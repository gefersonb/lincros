
package com.pack.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pack.model.Lancamento;
import com.pack.repository.LancamentoRepository;
import com.pack.view.LancamentoResumo;

@Stateless
public class LancamentoService {

	@Inject
	private LancamentoRepository repository;

	public Lancamento save(@Valid @NotNull Lancamento lcto) {
		return this.repository.save(lcto);
	}

	public Lancamento delete(@Valid @NotNull Lancamento lcto) {
		return this.repository.delete(lcto);
	}

	public Lancamento getById(@NotNull Long id) {
		return this.repository.getById(id);
	}

	public List<Lancamento> findAll() {
		return this.repository.findAll();
	}

	public List<Lancamento> findAvg() {
		return this.repository.findAvg();
	}

	public LancamentoResumo getLancamentoResumo() {
		return this.repository.getLancamentoResumo();
	}

	public LancamentoResumo getLancamentoMaiores() {
		return this.repository.getLancamentoMaiores();
	}

}