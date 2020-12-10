package com.pack.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pack.model.Item;
import com.pack.model.Lancamento;
import com.pack.view.LancamentoResumido;
import com.pack.view.LancamentoResumo;

@RequestScoped
public class LancamentoRepository {

	@PersistenceContext
	private EntityManager em;

	public Lancamento save(Lancamento lcto) {
		if (lcto.getId() == null) {
			this.em.persist(lcto);
		} else {
			this.em.merge(lcto);
		}
		this.em.flush();
		return lcto;
	}

	public Lancamento delete(Lancamento lcto) {
		Lancamento x = this.getById(lcto.getId());
		this.em.remove(x);
		this.em.flush();
		return lcto;
	}

	public Lancamento getById(Long id) {
		TypedQuery<Lancamento> tquery = this.em.createNamedQuery(Lancamento.GET_BY_ID, Lancamento.class);
		tquery.setParameter("id", id);
		Lancamento l = tquery.getSingleResult();
		l.calcularTotais();
		List<Item> itens = l.getItens();
		return l;
	}

	public List<Lancamento> findAll() {
		TypedQuery<Lancamento> tquery = this.em.createNamedQuery(Lancamento.FIND_ALL, Lancamento.class);
		List<Lancamento> lista = tquery.getResultList();
		List<Lancamento> list = new ArrayList<Lancamento>();
		for (Lancamento l : lista) {
			l.calcularTotais();
			list.add(l);
		}
		lista = null;
		return list;
	}

	public List<Lancamento> findAvg() {

		List<Lancamento> listAll = this.em.createNamedQuery(Lancamento.FIND_ALL, Lancamento.class).getResultList();
		List<Lancamento> lista = new ArrayList<Lancamento>();
		for (Lancamento l : listAll) {
			l.calcularTotais();
			if (l.getValorMedio() >= 100.) {
				lista.add(l);
			}

		}
		listAll = null;
		return lista;
	}

	public LancamentoResumo getLancamentoResumo() {
		LancamentoResumo lr = new LancamentoResumo();
		TypedQuery<LancamentoResumido> tquery = this.em.createNamedQuery(Lancamento.GET_RESUMIDO,
				LancamentoResumido.class);

		List<LancamentoResumido> list = tquery.getResultList();
		Double total = 0.;
		for (LancamentoResumido l : list) {
			if (l.getMedia() >= 100.) {
				lr.getLctos().add(l);
				total += l.getTotal();
			}
		}
		lr.setTotal(total);
		return lr;
	}

	public LancamentoResumo getLancamentoMaiores() {
		LancamentoResumo lr = new LancamentoResumo();
		TypedQuery<LancamentoResumido> tquery = this.em.createNamedQuery(Lancamento.GET_MAIORES,
				LancamentoResumido.class);
		int count = 0;
		List<LancamentoResumido> list = tquery.getResultList();
		Double total = 0.;
		for (LancamentoResumido l : list) {
			if (l.getTotal() > 50.) {
				if (count < 10) {
					lr.getLctos().add(l);
					total += l.getTotal();
					count++;
				} else {
					lr.setTotal(total);
					return lr;
				}
			}
		}
		lr.setTotal(total);
		return lr;
	}

}