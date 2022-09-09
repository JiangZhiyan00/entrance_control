package com.hhy.job.executor.stationInfo.entity;

/**
 * @author iAdmin <br/>
 *         表名： crm_station_info <br/>
 *         描述：StationInfo查询类 <br/>
 */
public class StationInfoParam extends StationInfo{

	private static final long serialVersionUID = 18L;
  	
	private int pageNumber;
	
	private int limit;
	
	private int offest;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffest() {
		return offest;
	}

	public void setOffest(int offest) {
		this.offest = offest;
	}
}
