package mb.dsam.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.Memoria;
@Stateless
public class MemoriaDao implements Serializable {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Memoria memoria) {
		this.manager.persist(memoria);
	}

	public Memoria busca(Long id) {
		return this.manager.find(Memoria.class, id);
	}

	public List<Memoria> lista() {
		return this.manager.createQuery("select m from Memoria m", Memoria.class)
				.getResultList();
	}

	public void remove(Memoria memoria) {
		Memoria memoriaParaRemover = this.manager.find(Memoria.class, memoria.getId());
		this.manager.remove(memoriaParaRemover);
	}
	
	public Memoria altera(Memoria memoria){
		return this.manager.merge(memoria);
	}
	
}
