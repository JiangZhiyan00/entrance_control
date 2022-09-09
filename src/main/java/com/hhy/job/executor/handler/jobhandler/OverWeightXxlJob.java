package com.hhy.job.executor.handler.jobhandler;

import com.hhy.job.executor.stationInfo.entity.StationInfo;
import com.hhy.job.executor.stationInfo.service.api.IStationInfoService;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyze;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.enums.IllegalTypeEnum;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.service.api.ITrfPersuadeRetIllCarAnalyzeService;
import com.hhy.job.executor.utils.ApiUtils;
import com.hhy.job.executor.utils.DateUtils;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 */
@Component
public class OverWeightXxlJob {

    private static Logger logger = LoggerFactory.getLogger(OverWeightXxlJob.class);

    @Autowired
    private ApiUtils apiUtils;

    @Autowired
    private ITrfPersuadeRetIllCarAnalyzeService trfService;

    @Autowired
    private IStationInfoService stationInfoService;

    private static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * 仅超重
     */
    @XxlJob(value = "overWeightHandler", init = "init", destroy = "destroy")
    public void overWeightHandler() {
        XxlJobHelper.log("---------------------------------程序开始---------------------------------");

        // 01获取发现时间为近10天+违法类型为超重的数据
        String todayStr = DateUtils.dateFormat(new Date());
        Date day10Ago = new Date(System.currentTimeMillis() - 3600L * 24 * 1000 * 9);
        String dateStr = DateUtils.dateFormat(day10Ago);
        XxlJobHelper.log("开始从简道云查询:"+dateStr+"至"+todayStr+"(今日)的违法(仅超重)数据...........");
        List<Map<String, Object>> formData = apiUtils.getAllFormData(null, this.getOverWeightFilter(day10Ago));
        XxlJobHelper.log(dateStr+"至"+todayStr+"(今日)的简道云违法(仅超重)数据共"+formData.size()+"条...........");
        //按日期+管理中心+收费站分组统计
        Map<String, Map<String, Map<String, Long>>> collect = formData.parallelStream().collect(
                Collectors.groupingBy(m -> m.get("_widget_1567995729841").toString().substring(0, 10).replaceAll("-", ""),//发现日期
                        Collectors.groupingBy(m -> m.get("_widget_1618897704064").toString(),//管理中心
                                Collectors.groupingBy(c -> c.get("_widget_1618897703954").toString(), Collectors.counting()))));//收费站

        int insertNum = 0;
        int updateNum = 0;
        for (Map.Entry<String, Map<String, Map<String, Long>>> entry1 : collect.entrySet()) {
            //日期
            String date = entry1.getKey();
            for (Map.Entry<String, Map<String, Long>> entry2 : entry1.getValue().entrySet()) {
                //管理中心名称
                String center = entry2.getKey();
                for (Map.Entry<String, Long> entry3 : entry2.getValue().entrySet()) {
                    //收费站名称
                    String stationName = entry3.getKey();
                    //数量
                    long value = entry3.getValue() == null? 0L:entry3.getValue();

                    //获取路段名称和收费站编码等信息
                    StationInfo stationInfo = new StationInfo();
                    stationInfo.setCentreName(center);
                    stationInfo.setStationName(stationName);
                    stationInfo.setIsdeleted("N");
                    List<StationInfo> infos = stationInfoService.selectForList(stationInfo);
                    if (infos.isEmpty()){
                        stationInfo.setRoadName("unknown");
                        stationInfo.setStationCode("unknown");
                    }else {
                        stationInfo = infos.get(0);
                    }

                    TrfPersuadeRetIllCarAnalyze trf = new TrfPersuadeRetIllCarAnalyze();
                    //以下4个参数值不会重复
                    trf.setCentername(center);
                    trf.setStationname(stationName);
                    trf.setDate(date);
                    trf.setTypename(IllegalTypeEnum.TYPE_5.getDescription());
                    List<TrfPersuadeRetIllCarAnalyze> list = trfService.selectForList(trf);
                    if (!list.isEmpty()){
                        trf = list.get(0);
                        updateNum++;
                    }else {
                        insertNum++;
                    }
                    trf.setTypecount(String.valueOf(value));
                    trf.setRoadname(stationInfo.getRoadName());
                    trf.setStationid(stationInfo.getStationCode());
                    trf.setType(IllegalTypeEnum.TYPE_5.getCode());
                    trfService.saveOne(trf);
                }
            }
        }
        XxlJobHelper.log("本次共新增:" + insertNum +"条数据,更新:"+updateNum + "条数据......");
        XxlJobHelper.log("---------------------------------程序结束---------------------------------");
    }

    /**
     * 获取超重车数据筛选过滤器
     * @param date 筛选日期
     */
    private Map<String, Object> getOverWeightFilter(Date date) {
        //_widget_1567995729841:发现时间;_widget_1567995730084:违法类型
        String[] field = {"_widget_1567995729841","_widget_1567995730084"};
        // 筛选条件
        List<Map<String,Object>> cond = new ArrayList<>(field.length);
        // 简道云时间使用UTC格式,慢8小时,需要处理下
        String dateBegin = DateUtils.dateFormat(DateUtils.getDateBegin(date),TIME_FORMAT);
        String dateEnd = DateUtils.dateFormat(DateUtils.getDateEnd(new Date()),TIME_FORMAT);
        cond.add(new HashMap<String,Object>(3){
            {
                put("field",field[0]);
                put("method","range");
                put("value", new String[]{dateBegin,dateEnd});
            }
        });
        cond.add(new HashMap<String,Object>(3){
            {
                put("field",field[1]);
                put("method","eq");
                put("value", "超重");
            }
        });
        return new HashMap<String,Object>(2){
            {
                put("rel", "and");
                put("cond", cond);
            }
        };
    }


    public void init(){
        logger.info("init");
    }

    public void destroy(){
        logger.info("destroy");
    }
}
