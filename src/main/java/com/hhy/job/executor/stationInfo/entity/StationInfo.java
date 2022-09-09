package com.hhy.job.executor.stationInfo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author iAdmin <br/>
 *         表名： crm_station_info <br/>
 *         描述：沪杭甬收费站/互通基础数据表（与hhy_dw.dwd_etcs_hhy_station_info同步） <br/>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationInfo implements Serializable {

	private static final long serialVersionUID = 18L;
	protected String id;// 主键
	protected String centreName;// 中心名称
	protected String cuName;// 管理处
	protected String companyName;// 所属公司名称
	protected String companyCode;// 公司编号
	protected String roadName;// 所属路名称
	protected String roadCode;// 所属路编码
	protected String sectionName;// 路段
	protected String stationName;// 收费站名
	protected String stationCode;// 站点编码
	protected String stationHex;// 收费站hex
	protected String status;// 开关状态(开-Y关-N)
	protected Long createUser;// 创建人ID
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date createTime;// 创建时间
	protected Long updateUser;// 更新人ID
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date lastUpdateTime;// 最后更新时间
	protected String isdeleted;// 删除状态(Y/N)
	protected String lon;// 经度
	protected String lat;// 纬度
	protected String sort;// 排序
	protected String offTheSite;// 就近驶离站点
	protected String isDescribe;// 是否调离
	protected String group_;// 所属单元

	public StationInfo() {
		super();
	}
	
  	public StationInfo(String id) {
  		super();
  		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getStationHex() {
		return stationHex;
	}
	public void setStationHex(String stationHex) {
		this.stationHex = stationHex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOffTheSite() {
		return offTheSite;
	}
	public void setOffTheSite(String offTheSite) {
		this.offTheSite = offTheSite;
	}
	public String getIsDescribe() {
		return isDescribe;
	}
	public void setIsDescribe(String isDescribe) {
		this.isDescribe = isDescribe;
	}
	public String getGroup_() {
		return group_;
	}
	public void setGroup_(String group_) {
		this.group_ = group_;
	}
}
