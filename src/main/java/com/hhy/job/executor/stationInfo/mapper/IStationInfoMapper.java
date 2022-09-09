package com.hhy.job.executor.stationInfo.mapper;

import com.hhy.job.executor.stationInfo.entity.StationInfo;
import com.hhy.job.executor.stationInfo.entity.StationInfoParam;
import com.hhy.job.executor.stationInfo.entity.StationInfoVo;

import java.util.List;
import java.util.Map;

public interface IStationInfoMapper {
	
	/*保存对象*/
	void insert(StationInfo stationInfo);
	
	//物理删除
	void deleteByPrimaryKeys(Map<String, Object> map);
	
	void deleteByPrimaryKey(Map<String, Object> map);
	
	//逻辑删除
	void logicDeleteByPrimaryKeys(Map<String, Object> map);
	
	void logicDeleteByPrimaryKey(Map<String, Object> map);
	
	/*根据非空属性更新对象信息*/
	void updateIgnoreNull(StationInfo stationInfo);
	
	/**更新*/
	void update(StationInfo stationInfo);
	
	/*分页查询对象*/
	List<StationInfoVo> queryStationInfoForList(StationInfoParam stationInfoParam);
	
	/*数据总量查询*/
	long queryTotalStationInfos(StationInfoParam stationInfoParam);
	
	/*根据主键查询对象*/
	StationInfo selectStationInfoByPrimaryKey(StationInfo stationInfo);
	
	/*根据部分属性对象查询全部结果，不分页*/
	List<StationInfo> selectForList(StationInfo stationInfo);
	
	/**
	 * 数据唯一性验证
	 * */
	List<StationInfo> selectForUnique(StationInfo stationInfo);
	
}