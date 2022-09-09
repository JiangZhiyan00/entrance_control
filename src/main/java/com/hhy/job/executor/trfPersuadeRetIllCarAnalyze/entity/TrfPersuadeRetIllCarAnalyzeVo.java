package com.hhy.job.executor.trfPersuadeRetIllCarAnalyze.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： dws_trf_persuade_ret_ill_car_analyze <br/>
 *         描述：劝返与违法车辆分析 <br/>
 */
public class TrfPersuadeRetIllCarAnalyzeVo extends TrfPersuadeRetIllCarAnalyze {

	private static final long serialVersionUID = 18L;

	private List<TrfPersuadeRetIllCarAnalyzeVo> trfPersuadeRetIllCarAnalyzeList;

	public TrfPersuadeRetIllCarAnalyzeVo() {
		super();
	}

  	public TrfPersuadeRetIllCarAnalyzeVo(Integer dwstrfpersuaderetillcaranalyzeid) {
  		super();
  		this.dwstrfpersuaderetillcaranalyzeid = dwstrfpersuaderetillcaranalyzeid;
	}

	public List<TrfPersuadeRetIllCarAnalyzeVo> getTrfPersuadeRetIllCarAnalyzeList() {
		return trfPersuadeRetIllCarAnalyzeList;
	}

	public void setTrfPersuadeRetIllCarAnalyzeList(List<TrfPersuadeRetIllCarAnalyzeVo> trfPersuadeRetIllCarAnalyzeList) {
		this.trfPersuadeRetIllCarAnalyzeList = trfPersuadeRetIllCarAnalyzeList;
	}

}
