package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.service.impl;

import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyze;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyzeParam;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.mapper.ITrfPersuadeRetIllCarAnalyzeMapper;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.service.api.ITrfPersuadeRetIllCarAnalyzeService;
import com.xxl.job.core.context.XxlJobHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TrfPersuadeRetIllCarAnalyzeServiceImpl implements ITrfPersuadeRetIllCarAnalyzeService {
	
	@Resource
	private ITrfPersuadeRetIllCarAnalyzeMapper trfPersuadeRetIllCarAnalyzeMapper;

	@Override
	public void insert(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze){
		trfPersuadeRetIllCarAnalyze.setInputtime(new Date());
		trfPersuadeRetIllCarAnalyze.setCreatedAt(new Date());
		trfPersuadeRetIllCarAnalyze.setUpdatedAt(new Date());
		trfPersuadeRetIllCarAnalyzeMapper.insert(trfPersuadeRetIllCarAnalyze);
	}

	@Override
	public void deleteByPrimaryKeys(Map<String,Object> map){
		trfPersuadeRetIllCarAnalyzeMapper.logicDeleteByPrimaryKeys(map);
	}
  
  	/**
	 * 删除一个对象
	 * */
	@Override
	public void deleteByPrimaryKey(Map<String, Object> map) {
		trfPersuadeRetIllCarAnalyzeMapper.logicDeleteByPrimaryKey(map);
	}

	@Override
	public void updateIgnoreNull(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze){
		trfPersuadeRetIllCarAnalyze.setInputtime(new Date());
		trfPersuadeRetIllCarAnalyze.setUpdatedAt(new Date());
		trfPersuadeRetIllCarAnalyzeMapper.updateIgnoreNull(trfPersuadeRetIllCarAnalyze);
	}
	
	/**
	* 更新
	*/
	@Override
	public void update(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze){
		trfPersuadeRetIllCarAnalyzeMapper.update(trfPersuadeRetIllCarAnalyze);
	}

	//@Override
	//public List<TrfPersuadeRetIllCarAnalyzeVo> queryTrfPersuadeRetIllCarAnalyzeByPage(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam) {
    //  	//分页
    //  	PageHelper.startPage(trfPersuadeRetIllCarAnalyzeParam.getPageNumber(),trfPersuadeRetIllCarAnalyzeParam.getLimit());
	//	return trfPersuadeRetIllCarAnalyzeMapper.queryTrfPersuadeRetIllCarAnalyzeForList(trfPersuadeRetIllCarAnalyzeParam);
	//}

	@Override
	public TrfPersuadeRetIllCarAnalyze selectTrfPersuadeRetIllCarAnalyzeByPrimaryKey(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze) {
		return trfPersuadeRetIllCarAnalyzeMapper.selectTrfPersuadeRetIllCarAnalyzeByPrimaryKey(trfPersuadeRetIllCarAnalyze);
	}

	@Override
	public long queryTotalTrfPersuadeRetIllCarAnalyzes(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam) {
		return trfPersuadeRetIllCarAnalyzeMapper.queryTotalTrfPersuadeRetIllCarAnalyzes(trfPersuadeRetIllCarAnalyzeParam);
	}
	
	/**
	*根据部分属性对象查询全部结果，不分页
	*/
	@Override
	public List<TrfPersuadeRetIllCarAnalyze> selectForList(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze){
		return trfPersuadeRetIllCarAnalyzeMapper.selectForList(trfPersuadeRetIllCarAnalyze);
	}
	
	/**
	 * 数据唯一性验证
	 * */
	@Override
	public boolean validateUniqueParam(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze) {
		return trfPersuadeRetIllCarAnalyzeMapper.selectForUnique(trfPersuadeRetIllCarAnalyze).size() == 0;
	}
	
	/**
	 * 保存单个对象
	 * <p>存在则更新，不存在则新增
	 * */
	@Override
	public void saveOne(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze) {
      	if(trfPersuadeRetIllCarAnalyze.getDwstrfpersuaderetillcaranalyzeid() == null) {
			this.insert(trfPersuadeRetIllCarAnalyze);
			//XxlJobHelper.log("新增成功"+trfPersuadeRetIllCarAnalyze + "..........................");
		}else {
			this.updateIgnoreNull(trfPersuadeRetIllCarAnalyze);
			//XxlJobHelper.log("更新成功"+trfPersuadeRetIllCarAnalyze + "..........................");
		}
	}
	
	/**
	 * 保存多个用户
	 * <p>存在则更新，不存在则新增
	 * */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void multipleSaveAndEdit(TrfPersuadeRetIllCarAnalyze[] objs) {
		for(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze : objs) {
			this.saveOne(trfPersuadeRetIllCarAnalyze);
		}
	}
}
