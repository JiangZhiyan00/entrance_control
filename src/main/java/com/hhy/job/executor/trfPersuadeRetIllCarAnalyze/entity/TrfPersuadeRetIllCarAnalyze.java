package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity;

import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author iAdmin <br/>
 *         表名： dws_trf_persuade_ret_ill_car_analyze <br/>
 *         描述：劝返与违法车辆分析 <br/>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrfPersuadeRetIllCarAnalyze implements Serializable {

	private static final long serialVersionUID = 18L;
	protected Integer dwstrfpersuaderetillcaranalyzeid;// 劝返与违法车辆分析id
	protected String centername;// 管理中心
	protected String roadname;// 路段名称
	protected String stationid;// 收费站编号
	protected String stationname;// 收费站名称
	protected String date;// 日期，包括年月日
	protected String typecount;// 分类数量
	protected String type;// 违法类型，1-14
	protected String typename;// 违法类型名称
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date inputtime;// 推送时间，格式：年-月-日 时:分:秒
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date createdAt;// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date updatedAt;// 修改时间

	public TrfPersuadeRetIllCarAnalyze() {
		super();
	}
	
  	public TrfPersuadeRetIllCarAnalyze(Integer dwstrfpersuaderetillcaranalyzeid) {
  		super();
  		this.dwstrfpersuaderetillcaranalyzeid = dwstrfpersuaderetillcaranalyzeid;
	}
	public Integer getDwstrfpersuaderetillcaranalyzeid() {
		return dwstrfpersuaderetillcaranalyzeid;
	}
	public void setDwstrfpersuaderetillcaranalyzeid(Integer dwstrfpersuaderetillcaranalyzeid) {
		this.dwstrfpersuaderetillcaranalyzeid = dwstrfpersuaderetillcaranalyzeid;
	}
	public String getCentername() {
		return centername;
	}
	public void setCentername(String centername) {
		this.centername = centername;
	}
	public String getRoadname() {
		return roadname;
	}
	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
	public String getStationname() {
		return stationname;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTypecount() {
		return typecount;
	}
	public void setTypecount(String typecount) {
		this.typecount = typecount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Trf{" +
				"id=" + dwstrfpersuaderetillcaranalyzeid +
				", centername='" + centername + '\'' +
				", roadname='" + roadname + '\'' +
				", stationid='" + stationid + '\'' +
				", stationname='" + stationname + '\'' +
				", date='" + date + '\'' +
				", typecount='" + typecount + '\'' +
				", type='" + type + '\'' +
				", typename='" + typename + '\'' +
				", inputtime=" + inputtime +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
