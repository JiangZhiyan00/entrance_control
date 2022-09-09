package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.service.api;

import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyze;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyzeParam;
import com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity.TrfPersuadeRetIllCarAnalyzeVo;

import java.util.List;
import java.util.Map;

public interface ITrfPersuadeRetIllCarAnalyzeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	void insert(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);

	/**
	 * 按对象中的主键进行删除，
	 */
	void deleteByPrimaryKeys(Map<String, Object> map);
  
  	/**
	 * 按对象中的主键进行删除，
	 */
	void deleteByPrimaryKey(Map<String, Object> map);
		
	/**
	 * 按对象中的主键进行所有非空属性的修改
	 */
	void updateIgnoreNull(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	* 更新
	*/
	void update(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	 *根据页面查询条件查询数据并分页
	 */
	//List<TrfPersuadeRetIllCarAnalyzeVo> queryTrfPersuadeRetIllCarAnalyzeByPage(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam);
	
	/**
	 *分页查询总条数
	 */
	long queryTotalTrfPersuadeRetIllCarAnalyzes(TrfPersuadeRetIllCarAnalyzeParam trfPersuadeRetIllCarAnalyzeParam);
  
	
	/**
	 *通过ID查询数据
	 */
	TrfPersuadeRetIllCarAnalyze selectTrfPersuadeRetIllCarAnalyzeByPrimaryKey(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	*根据部分属性对象查询全部结果，不分页
	*/
	List<TrfPersuadeRetIllCarAnalyze> selectForList(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	 * 数据唯一性验证
	 *<P>代码生成，必要时可以使用
	 * */
	boolean validateUniqueParam(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	* 保存单个对象
	* <P>存在则更新，不存在则新增
	*/
	void saveOne(TrfPersuadeRetIllCarAnalyze trfPersuadeRetIllCarAnalyze);
	
	/**
	* 保存多个对象
	*/
	void multipleSaveAndEdit(TrfPersuadeRetIllCarAnalyze[] objs);
	
}