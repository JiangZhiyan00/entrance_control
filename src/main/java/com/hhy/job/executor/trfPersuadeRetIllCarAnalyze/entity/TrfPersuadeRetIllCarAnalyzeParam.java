package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity;

/**
 * @author iAdmin <br/>
 *         表名： dws_trf_persuade_ret_ill_car_analyze <br/>
 *         描述：TrfPersuadeRetIllCarAnalyze查询类 <br/>
 */
public class TrfPersuadeRetIllCarAnalyzeParam extends TrfPersuadeRetIllCarAnalyze{

	private static final long serialVersionUID = 18L;
  	
	private int pageNumber;
	
	private int limit;
	
	private int offset;

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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
