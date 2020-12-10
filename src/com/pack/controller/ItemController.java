package com.pack.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.pack.model.Item;
import com.pack.services.ItemService;

@ViewScoped
@ManagedBean(name = "itemController")
public class ItemController extends AbstractController<Item> {

	private static final Logger log = Logger.getLogger(ItemController.class);

	private static final long serialVersionUID = 1L;

	private Integer state = 0;
	private Item item = new Item();
	private List<Item> itens = new ArrayList<Item>();
	private String aviso = "";

	@Inject
	private ItemService itemService;

	@Override
	public Item createModel() {
		return new Item();
	}

	public void consultar() {
		this.itens = this.itemService.findAll();
	}

	public void alterar(Item item) {
		this.item = this.itemService.getById(item.getId());
		this.state = 1;
	}

	public void criar() {
		this.item = new Item();
		this.state = 1;
	}

	public void salvar() {
		this.itemService.save(this.item);
		this.consultar();
		this.state = 0;
	}

	public void delete(Item item) {
		this.item = this.itemService.getById(item.getId());
		if (this.item.getLancamentos().size() == 0)
			this.itemService.delete(item);
		else
			this.aviso = "Não é possível excluir um Item que esteja vinculado a Lançamentos";
		this.consultar();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public void voltar() {
		this.state = 0;
	}

}