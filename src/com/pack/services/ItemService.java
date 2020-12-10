

package com.pack.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pack.model.Item;
import com.pack.repository.ItemRepository;

@Stateless
public class ItemService {

	@Inject
	private ItemRepository repository;

	public Item save(@Valid @NotNull Item item) {
		return this.repository.save(item);
	}

	public Item delete(@Valid @NotNull Item lcto) {
		return this.repository.delete(lcto);
	}

	public Item getById(@NotNull Long id) {
		return this.repository.getById(id);
	}

	public List<Item> findAll() {
		return this.repository.findAll();
	}

}