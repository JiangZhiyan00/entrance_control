package com.hhy.job.executor.stationInfo.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： crm_station_info <br/>
 *         描述：沪杭甬收费站/互通基础数据表（与hhy_dw.dwd_etcs_hhy_station_info同步） <br/>
 */
public class StationInfoVo extends StationInfo {

	private static final long serialVersionUID = 18L;

	private List<StationInfoVo> stationInfoList;

	public StationInfoVo() {
		super();
	}

  	public StationInfoVo(String id) {
  		super();
  		this.id = id;
	}

	public List<StationInfoVo> getStationInfoList() {
		return stationInfoList;
	}

	public void setStationInfoList(List<StationInfoVo> stationInfoList) {
		this.stationInfoList = stationInfoList;
	}

}
