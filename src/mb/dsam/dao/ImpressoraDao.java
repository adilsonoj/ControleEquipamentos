package mb.dsam.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.Impressora;
@Stateless
public class ImpressoraDao implements Serializable {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Impressora impressora) {
		this.manager.persist(impressora);
	}

	public Impressora busca(Long id) {
		return this.manager.find(Impressora.class, id);
	}

	public List<Impressora> lista() {
		return this.manager.createQuery("select m from Impressora m", Impressora.class)
				.getResultList();
	}

	public void remove(Impressora impressora) {
		Impressora impressoraParaRemover = this.manager.find(Impressora.class, impressora.getNumeroPatrimonial());
		this.manager.remove(impressoraParaRemover);
	}
	
	public Impressora altera(Impressora impressora){
		return this.manager.merge(impressora);
	}
}
