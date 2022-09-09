package com.hhy.job.executor.stationInfo.service.impl;

import com.hhy.job.executor.stationInfo.entity.StationInfo;
import com.hhy.job.executor.stationInfo.entity.StationInfoParam;
import com.hhy.job.executor.stationInfo.mapper.IStationInfoMapper;
import com.hhy.job.executor.stationInfo.service.api.IStationInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StationInfoServiceImpl implements IStationInfoService {
	
	@Autowired
	private IStationInfoMapper stationInfoMapper;

	@Override
	public void insert(StationInfo stationInfo){
		stationInfo.setCreateTime(new Date());//创建时间
		stationInfo.setLastUpdateTime(new Date());//更新时间
		stationInfo.setIsdeleted("N");//设置默认值2，未删除
		stationInfoMapper.insert(stationInfo);
	}

	public void deleteByPrimaryKeys(Map<String,Object> map){
		stationInfoMapper.logicDeleteByPrimaryKeys(map);
	}
  
  	/**
	 * 删除一个对象
	 * */
	@Override
	public void deleteByPrimaryKey(Map<String, Object> map) {
		stationInfoMapper.logicDeleteByPrimaryKey(map);
	}
	
	public void updateIgnoreNull(StationInfo stationInfo){
		stationInfo.setLastUpdateTime(new Date());//更新时间
		stationInfoMapper.updateIgnoreNull(stationInfo);
	}
	
	/**
	* 更新
	*/
	public void update(StationInfo stationInfo){
		stationInfo.setLastUpdateTime(new Date());//更新时间
		stationInfoMapper.update(stationInfo);
	}
	
/*	public List<StationInfoVo> queryStationInfoByPage(StationInfoParam stationInfoParam) {
      	//分页
      	PageHelper.startPage(stationInfoParam.getPageNumber(),stationInfoParam.getLimit());
		return stationInfoMapper.queryStationInfoForList(stationInfoParam);
	}*/
	

	public StationInfo selectStationInfoByPrimaryKey(StationInfo StationInfo) {
		return stationInfoMapper.selectStationInfoByPrimaryKey(StationInfo);
	}
	
	public long queryTotalStationInfos(StationInfoParam stationInfoParam) {
		return stationInfoMapper.queryTotalStationInfos(stationInfoParam);
	}
	
	/**
	*根据部分属性对象查询全部结果，不分页
	*/
	public List<StationInfo> selectForList(StationInfo stationInfo){
		return stationInfoMapper.selectForList(stationInfo);
	}
	
	/**
	 * 数据唯一性验证
	 * */
	@Override
	public boolean validateUniqueParam(StationInfo stationInfo) {
		return stationInfoMapper.selectForUnique(stationInfo).size() == 0;
	}
	
	/**
	 * 保存单个对象
	 * <p>存在则更新，不存在则新增
	 * */
	@Override
	public void saveOne(StationInfo stationInfo) {
		if(StringUtils.isBlank(stationInfo.getId())) {
			this.insert(stationInfo);
		}else {
			this.updateIgnoreNull(stationInfo);
		}
	}
	
	/**
	 * 保存多个用户
	 * <p>存在则更新，不存在则新增
	 * */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void multipleSaveAndEdit(StationInfo[] objs) {
		for(StationInfo stationInfo : objs) {
			this.saveOne(stationInfo);
		}
	}
}
