package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.mapper;

import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyze;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyzeParam;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyzeVo;

import java.util.List;
import java.util.Map;

public interface ITrfPersuadeRetIllCarAnalyzeMapper {
	
	/*保存对象*/
	void insert(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	//物理删除
	void deleteByPrimaryKeys(Map<String, Object> map);
	
	void deleteByPrimaryKey(Map<String, Object> map);
	
	//逻辑删除
	void logicDeleteByPrimaryKeys(Map<String, Object> map);
	
	void logicDeleteByPrimaryKey(Map<String, Object> map);
	
	/*根据非空属性更新对象信息*/
	void updateIgnoreNull(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**更新*/
	void update(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/*分页查询对象*/
	List<TrfPersuadeRetIllCarAnalyzeVo> queryTrfPersuadeRetIllCarAnalyzeForList(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam);
	
	/*数据总量查询*/
	long queryTotalTrfPersuadeRetIllCarAnalyzes(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam);
	
	/*根据主键查询对象*/
	TrfPersuadeRetIllCarAnalyze selectTrfPersuadeRetIllCarAnalyzeByPrimaryKey(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/*根据部分属性对象查询全部结果，不分页*/
	List<TrfPersuadeRetIllCarAnalyze> selectForList(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	 * 数据唯一性验证
	 * */
	List<TrfPersuadeRetIllCarAnalyze> selectForUnique(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
}