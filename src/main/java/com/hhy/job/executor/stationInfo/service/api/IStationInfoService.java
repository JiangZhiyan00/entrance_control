package com.hhy.job.executor.stationInfo.service.api;

import com.hhy.job.executor.stationInfo.entity.StationInfo;
import com.hhy.job.executor.stationInfo.entity.StationInfoParam;
import com.hhy.job.executor.stationInfo.entity.StationInfoVo;

import java.util.List;
import java.util.Map;

public interface IStationInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	void insert(StationInfo stationInfo);

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
	void updateIgnoreNull(StationInfo stationInfo);
	
	/**
	* 更新
	*/
	void update(StationInfo stationInfo);
	
	/**
	 *根据页面查询条件查询数据并分页
	 */
//	List<StationInfoVo> queryStationInfoByPage(StationInfoParam stationInfoParam);
	
	/**
	 *分页查询总条数
	 */
	long queryTotalStationInfos(StationInfoParam stationInfoParam);
  
	
	/**
	 *通过ID查询数据
	 */
	StationInfo selectStationInfoByPrimaryKey(StationInfo stationInfo);
	
	/**
	*根据部分属性对象查询全部结果，不分页
	*/
	List<StationInfo> selectForList(StationInfo stationInfo);
	
	/**
	 * 数据唯一性验证
	 *<P>代码生成，必要时可以使用
	 * */
	boolean validateUniqueParam(StationInfo stationInfo);
	
	/**
	* 保存单个对象
	* <P>存在则更新，不存在则新增
	*/
	void saveOne(StationInfo stationInfo);
	
	/**
	* 保存多个对象
	*/
	void multipleSaveAndEdit(StationInfo[] objs);
	
}