package com.pack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.pack.domain.AbstractEntity;
import com.pack.util.DateUtils;

@Entity
@Table(schema = "PUBLIC", name = "LANCAMENTO")
@NamedQueries(value = { @NamedQuery(name = Lancamento.FIND_ALL, query = "SELECT l FROM Lancamento l"),
		@NamedQuery(name = Lancamento.FIND_AVG, query = "SELECT l FROM Lancamento l"),
		@NamedQuery(name = Lancamento.GET_BY_ID, query = "SELECT l FROM Lancamento l WHERE l.id = :id"),
		@NamedQuery(name = Lancamento.GET_RESUMIDO, query = "SELECT new com.pack.view.LancamentoResumido(l, sum(i.valor), avg(i.valor), count(i.id)) FROM Item i JOIN i.lancamentos l GROUP BY l.id"),
		@NamedQuery(name = Lancamento.GET_MAIORES, query = "SELECT new com.pack.view.LancamentoResumido(l, sum(i.valor), avg(i.valor), count(i.id)) FROM Item i JOIN i.lancamentos l WHERE i.descricao LIKE 'A%' GROUP BY l.id ORDER BY SUM(i.valor) DESC"),

})

public class Lancamento implements AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Lancamento.findAll";
	public static final String GET_BY = "Lancamento.getBy";
	public static final String FIND_AVG = "Lancamento.findAvg";
	public static final String GET_BY_ID = "Lancamento.getById";
	public static final String GET_RESUMIDO = "Lancamento.getResumido";
	public static final String GET_MAIORES = "Lancamento.getMaiores";

	@Id
	@SequenceGenerator(name = "LANCAMENTO_ID_GENERATOR", schema = "PUBLIC", sequenceName = "SEQ_LANCAMENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANCAMENTO_ID_GENERATOR")
	@Column(name = "OID")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INICIAL", nullable = false)
	private Date dtInicial;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FINAL", nullable = false)
	private Date dtFinal;

	@NotNull
	@Column(name = "OBSERVACAO", length = 1000)
	private String observacao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "lancamentoitem", joinColumns = { @JoinColumn(name = "oid_lancamento") }, inverseJoinColumns = {
			@JoinColumn(name = "oid_item") })
	private List<Item> itens = new ArrayList<Item>();

	@Transient
	private Double valorMedio;

	@Transient
	private Double valorTotal;

	@Transient
	private Integer qtItens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void calcularTotais() {
		this.valorMedio = 0.;
		this.valorTotal = 0.;
		this.qtItens = 0;
		int q = itens.size();
		Double t = 0.;
		for (Item x : itens) {
			t += x.getValor();
		}
		if (q>0) {
			this.valorMedio = t / q;
			this.valorTotal = t;
			this.qtItens = q;
		}
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Double getValorMedio() {
		return valorMedio;
	}

	public Integer getQtItens() {
		return qtItens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public String getDtInicialFmt() {
		return DateUtils.toString(this.dtInicial, "dd/MM/yyyy");
	}

	public String getDtFinalFmt() {
		return DateUtils.toString(this.dtFinal, "dd/MM/yyyy");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtFinal == null) ? 0 : dtFinal.hashCode());
		result = prime * result + ((dtInicial == null) ? 0 : dtInicial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (dtFinal == null) {
			if (other.dtFinal != null)
				return false;
		} else if (!dtFinal.equals(other.dtFinal))
			return false;
		if (dtInicial == null) {
			if (other.dtInicial != null)
				return false;
		} else if (!dtInicial.equals(other.dtInicial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lancamento [id=");
		builder.append(id);
		builder.append(", dtInicial=");
		builder.append(dtInicial);
		builder.append(", dtFinal=");
		builder.append(dtFinal);
		builder.append(", observacao=");
		builder.append(observacao);
		builder.append("]");
		return builder.toString();
	}

}
