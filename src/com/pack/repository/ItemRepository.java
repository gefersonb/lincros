package com.pack.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pack.model.Item;
import com.pack.model.Lancamento;

@RequestScoped
public class ItemRepository {

	@PersistenceContext
	private EntityManager em;

	public Item save(Item item) {
		if (item.getId() == null) {
			this.em.persist(item);
		} else {
			this.em.merge(item);
		}
		this.em.flush();
		return item;
	}

	public Item delete(Item item) {
		Item x = this.getById(item.getId());
		this.em.remove(x);
		this.em.flush();
		return item;
	}

	public Item getById(Long id) {
		TypedQuery<Item> tquery = this.em.createNamedQuery(Item.GET_BY_ID, Item.class);
		tquery.setParameter("id", id);
		Item i = tquery.getSingleResult();
		for (Lancamento l : i.getLancamentos())
			;
		return i;
	}

	public List<Item> findAll() {
		return this.em.createNamedQuery(Item.FIND_ALL, Item.class).getResultList();
	}

}