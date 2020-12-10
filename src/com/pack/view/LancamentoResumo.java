package com.pack.view;

import java.util.ArrayList;
import java.util.List;

public class LancamentoResumo {

	private List<LancamentoResumido> lctos = new ArrayList<LancamentoResumido>();
	private Double Total = 0.;
	public List<LancamentoResumido> getLctos() {
		return lctos;
	}
	public void setLctos(List<LancamentoResumido> lctos) {
		this.lctos = lctos;
	}
	public Double getTotal() {
		return Total;
	}
	public void setTotal(Double total) {
		Total = total;
	}
	
	
}
