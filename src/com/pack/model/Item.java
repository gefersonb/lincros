package com.pack.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.pack.domain.AbstractEntity;

@Entity
@Table(schema = "PUBLIC", name = "ITEM")
@NamedQueries(value = { @NamedQuery(name = Item.FIND_ALL, query = "SELECT i FROM Item i"),
		@NamedQuery(name = Item.GET_BY_ID, query = "SELECT i FROM Item i WHERE i.id = :id"),
		})

public class Item implements AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Item.findAll";
	public static final String GET_BY = "Item.getBy";
	public static final String GET_BY_ID = "Item.getById";

	@Id
	@SequenceGenerator(name = "ITEM_ID_GENERATOR", schema = "PUBLIC", sequenceName = "SEQ_ITEM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GENERATOR")
	@Column(name = "OID")
	private Long id;

	@NotNull
	@Column(name = "VALOR", nullable = false, length = 8, precision = 2)
	private Double valor;

	@NotNull
	@Column(name = "DESCRICAO", length = 255)
	private String descricao;

	@ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	@Transient
	private Boolean check;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", valor=");
		builder.append(valor);
		builder.append("]");
		return builder.toString();
	}

}
