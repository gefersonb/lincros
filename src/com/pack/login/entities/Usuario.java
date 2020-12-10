package com.pack.login.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.pack.domain.AbstractEntity;
import com.pack.util.StringUtils;
import com.pack.util.WebConstants;

/*
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "login", "nome", "email", "grupos" })
@XmlRootElement(name = "usuario")
*/
public class Usuario implements AbstractEntity<String> {

	private static final long serialVersionUID = 1L;

	//@XmlTransient
	private String id;

	//@XmlElement(required = true)
	private String login;

	//@XmlElement(required = true)
	private String nome;

	//@XmlElement(required = true)
	private String email;

	//@XmlElement(required = true)
	private List<String> grupos;

	@Override
	public String getId() {
		if (this.id == null) {
			this.id = this.getLogin();
		}
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String value) {
		this.login = value;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String value) {
		this.nome = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public List<String> getGrupos() {
		if (grupos == null) {
			grupos = new ArrayList<>();
		}
		return grupos;
	}

	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}

	public static class DateAdapter extends XmlAdapter<String, Date> {

		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		@Override
		public String marshal(Date v) throws Exception {
			return dateFormat.format(v);
		}

		@Override
		public Date unmarshal(String v) throws Exception {
			return dateFormat.parse(v);
		}

	}

	@Override
	public String toString() {
		String description = (this.getId() != null && !StringUtils.isNullOrEmpty(this.getNome()))
				? this.getId() + " - " + this.getNome() : (this.getId() != null ? this.getId().toString() : "");
		if (StringUtils.isNullOrEmpty(description)) {
			description = WebConstants.NAO_INFORMADO;
		}
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}