package com.pack.view;

import com.pack.model.Lancamento;

public class LancamentoResumido {

	private Lancamento lancamento;
	private Double media;
	private Double total;
	private Long count;
	
	public LancamentoResumido(Lancamento lancamento, Double total, Double media, Long count) {
		super();
		this.lancamento = lancamento;
		this.total = total;
		this.media = media;
		this.count = count;
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
