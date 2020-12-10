
package com.pack.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.pack.login.entities.Usuario;

@ViewScoped
@ManagedBean(name = "intersecaoController")
public class IntersecaoController extends AbstractController<Usuario> {

	private static final long serialVersionUID = 1L;

	private String aviso = "";
	private String resultado = "";
	private Long a=0l, b=0l, c=0l, d=0l;

	@Override
	public Usuario createModel() {
		// this.lancamentos = this.lancamentoService.findAll();
		return new Usuario();
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public Long getA() {
		return a;
	}

	public void setA(Long a) {
		this.a = a;
	}

	public Long getB() {
		return b;
	}

	public void setB(Long b) {
		this.b = b;
	}

	public Long getC() {
		return c;
	}

	public void setC(Long c) {
		this.c = c;
	}

	public Long getD() {
		return d;
	}

	public void setD(Long d) {
		this.d = d;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public void verificar() {
		this.aviso = "";
		this.resultado = "";
		if(this.a == null || this.b == null || this.c == null || this.d == null) {
			this.aviso = "Informe os valores para todas as variaveis.";
			return;
			
		}
			
		if(this.a > this.b) {
			this.aviso = "Variavel `A` deve ser menor ou igual a `B`.";
			return;
		}
		if(this.c > this.d) {
			this.aviso = "Variavel `C` deve ser menor ou igual a `D`.";
			return;
		}
		
		if(
				(this.a >= this.c && this.a <= this.d) ||
				(this.b >= this.c && this.b <= this.d) ||
				(this.c >= this.a && this.c <= this.b) ||
				(this.d >= this.a && this.d <= this.b) 
		)
			this.resultado = "Existe intersecao entre as faixas 1 e 2";
		else
			this.resultado = "Nao ha intersecao entre as faixas 1 e 2";
	}

}