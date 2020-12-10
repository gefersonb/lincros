
package com.pack.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.pack.model.Item;
import com.pack.model.Lancamento;
import com.pack.services.ItemService;
import com.pack.services.LancamentoService;
import com.pack.view.LancamentoResumo;

@ViewScoped
@ManagedBean(name = "lancamentoController")
public class LancamentoController extends AbstractController<Lancamento> {

	private static final Logger log = Logger.getLogger(LancamentoController.class);

	private static final long serialVersionUID = 1L;

	private Integer state = 0;
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private LancamentoResumo lancamentoMedio = new LancamentoResumo();
	private Lancamento lcto = new Lancamento();
	private Double vlTotal = 0.;
	private List<Item> itens;
	private Long idLcto = 0l;
	private List<Item> todosItens;
	private String aviso = "";

	@Inject
	private LancamentoService lancamentoService;

	@Inject
	private ItemService itemService;

	@Override
	public Lancamento createModel() {
		return new Lancamento();
	}

	public void voltar() {
		this.state = 0;
	}

	public void consultar() {
		this.aviso = "";
		this.lancamentos = this.lancamentoService.findAll();
	}

	public void consultarLctoMedio() {
		this.lancamentoMedio = this.lancamentoService.getLancamentoResumo();
	}

	public void consultarMaiores() {
		this.lancamentoMedio = this.lancamentoService.getLancamentoMaiores();
	}

	public void alterar(Lancamento lcto) {
		this.aviso = "";
		this.idLcto = lcto.getId();
		this.lcto = this.lancamentoService.getById(lcto.getId());
		this.itens = this.lcto.getItens();
		this.state = 1;
	}

	public void criar() {
		this.aviso = "";
		this.lcto = new Lancamento();
		this.state = 1;
	}

	public void salvar() {
		this.lcto = this.lancamentoService.save(this.lcto);
		
		this.consultar();
		this.idLcto = this.lcto.getId();
		
		//this.state = 0;
	}

	public void delete(Lancamento lcto) {
		this.aviso = "";
		this.lcto = this.lancamentoService.getById(lcto.getId());
		if (this.lcto.getItens().size() == 0)
			this.lancamentoService.delete(lcto);
		else
			this.aviso = "Não é possível excluir um Lançamento que esteja vinculado a Itens";

		//this.consultar();
	}

	public void deleteItem(Long _idLcto, Item item) {
		this.lcto = this.lancamentoService.getById(_idLcto);
		this.itens = this.lcto.getItens();
		this.lcto.getItens().remove(item);
		this.lancamentoService.save(this.lcto);
		this.lcto.calcularTotais();
		this.consultar();
	}

	public void incluirItens(Long _idLcto) {
		this.todosItens = new ArrayList<Item>();
		this.lcto = this.lancamentoService.getById(_idLcto);

		for (Item x : this.queryTodosItens()) {
			boolean jaIncluido = false;
			for (Item y : this.lcto.getItens()) {
				if (y.getId() == x.getId())
					jaIncluido = true;
			}
			Item n = new Item();
			n.setCheck(jaIncluido);
			n.setDescricao(x.getDescricao());
			n.setId(x.getId());
			n.setValor(x.getValor());
			this.todosItens.add(n);

		}
		this.state = 3;
	}

	public void confirmarItens(Long _idLcto) {
		this.lcto = this.lancamentoService.getById(_idLcto);
		while (this.lcto.getItens().size() > 0)
			this.lcto.getItens().remove(0);
		this.itens = this.lcto.getItens();
		for (Item x : this.todosItens) {
			if (x.getCheck()) {
				this.lcto.getItens().add(x);
			}
		}
		this.lancamentoService.save(this.lcto);
		this.lcto.calcularTotais();
		this.state = 1;
	}

	private List<Item> queryTodosItens() {
		return this.itemService.findAll();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento getLcto() {
		return lcto;
	}

	public void setLcto(Lancamento lcto) {
		this.lcto = lcto;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getVlTotal() {
		this.vlTotal = 0.;
		for (Item x : this.lcto.getItens()) {
			this.vlTotal += x.getValor();
		}
		return this.vlTotal;
	}

	public void setVlTotal(Double v) {
		this.vlTotal = v;
	}

	public List<Item> getItens() {
		this.itens = this.lcto.getItens();
		return this.itens;
	}

	public void mostrarItens(Long id) {
		this.idLcto = id;
		this.lcto = this.lancamentoService.getById(id);
		this.itens = this.lcto.getItens();
		this.state = 2;
	}

	public void mostrarLancamento(Long id) {
		this.lcto = this.lancamentoService.getById(id);
		this.itens = this.lcto.getItens();
		this.state = 1;
	}

	public Long getIdLcto() {
		return idLcto;
	}

	public List<Item> getTodosItens() {
		return todosItens;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public LancamentoResumo getLancamentoMedio() {
		return lancamentoMedio;
	}

	public void setLancamentoMedio(LancamentoResumo lancamentoMedio) {
		this.lancamentoMedio = lancamentoMedio;
	}

}