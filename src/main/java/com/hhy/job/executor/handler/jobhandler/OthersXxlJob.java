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

@Component
public class OthersXxlJob {

    private static Logger logger = LoggerFactory.getLogger(OthersXxlJob.class);

    @Autowired
    private ApiUtils apiUtils;

    @Autowired
    private ITrfPersuadeRetIllCarAnalyzeService trfService;

    @Autowired
    private IStationInfoService stationInfoService;

    private static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * 其他违法类型
     */
    @XxlJob(value = "othersHandler", init = "init", destroy = "destroy")
    public void othersHandler() {
        XxlJobHelper.log("---------------------------------程序开始---------------------------------");

        // 01获取发现时间为近10天的+违法类型不为超重的数据
        String todayStr = DateUtils.dateFormat(new Date());
        Date day10Ago = new Date(System.currentTimeMillis() - 3600L * 24 * 1000 * 9);
        String dateStr = DateUtils.dateFormat(day10Ago);
        XxlJobHelper.log("开始从简道云查询:"+dateStr+"至"+todayStr+"(今日)的违法(不含超重)数据...........");
        List<Map<String, Object>> formData = apiUtils.getAllFormData(null, this.getFilter(day10Ago));
        XxlJobHelper.log(dateStr+"至"+todayStr+"(今日)的简道云违法(不含超重)数据共"+formData.size()+"条...........");
        //按日期/管理中心/收费站/违法类型分组统计
        Map<String, Map<String, Map<String, Map<String, Long>>>> collect = formData.parallelStream().collect(
                Collectors.groupingBy(m -> m.get("_widget_1567995729841").toString().substring(0,10).replaceAll("-",""),//发现日期
                        Collectors.groupingBy(m -> m.get("_widget_1618897704064").toString(),//管理中心名称
                                Collectors.groupingBy(c -> c.get("_widget_1618897703954").toString(),//收费站名称
                                        Collectors.groupingBy(g -> g.get("_widget_1567995730084").toString(), Collectors.counting())))));//违法类型

        int insertNum = 0;
        int updateNum = 0;
        for (Map.Entry<String, Map<String, Map<String, Map<String, Long>>>> entry1 : collect.entrySet()) {
            //日期
            String date = entry1.getKey();
            for (Map.Entry<String, Map<String, Map<String, Long>>> entry2 : entry1.getValue().entrySet()) {
                //管理中心名称
                String center = entry2.getKey();
                for (Map.Entry<String, Map<String, Long>> entry3 : entry2.getValue().entrySet()) {
                    //收费站名称
                    String stationName = entry3.getKey();
                    for (Map.Entry<String, Long> entry4 : entry3.getValue().entrySet()) {
                        //违法类型
                        String typeName = entry4.getKey();
                        long value = entry4.getValue() == null? 0L:entry4.getValue();

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
                        trf.setTypename(typeName);
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
                        trf.setType(IllegalTypeEnum.getCodeByDescription(trf.getTypename()));
                        trfService.saveOne(trf);
                    }
                }
            }
        }
        XxlJobHelper.log("本次共新增:" + insertNum +"条数据,更新:"+updateNum + "条数据......");
        XxlJobHelper.log("---------------------------------程序结束---------------------------------");
    }

    /**
     * 获取数据筛选过滤器
     * @param date 筛选日期
     */
    private Map<String, Object> getFilter(Date date) {
        //查发现时间为指定日期到当前的,违法类型不是超重的,且流程状态为流转完成
        //_widget_1567995729841:发现时间;_widget_1567995730084:违法类型;flowState:流程状态
        String[] field = {"_widget_1567995729841","_widget_1567995730084","_widget_1567995730084","flowState"};
        // 筛选条件
        List<Map<String,Object>> cond = new ArrayList<>(field.length);
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
                put("method","ne");
                put("value", "超重");
            }
        });
        cond.add(new HashMap<String,Object>(3){
            {
                put("field",field[2]);
                put("method","not_empty");
            }
        });
        cond.add(new HashMap<String,Object>(3){
            {
                put("field",field[3]);
                put("method","eq");
                put("value", new int[]{1});
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


