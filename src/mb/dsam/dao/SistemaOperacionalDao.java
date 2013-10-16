package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.SistemaOperacional;

@Stateless
public class SistemaOperacionalDao {
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(SistemaOperacional so) {
		this.manager.persist(so);
	}

	public SistemaOperacional busca(Integer id) {
		return this.manager.find(SistemaOperacional.class, id);
	}

	public List<SistemaOperacional> lista() {
		return this.manager.createQuery("select c from SistemaOperacional c", SistemaOperacional.class)
				.getResultList();
	}

	public void remove(SistemaOperacional so) {
		SistemaOperacional soParaRemover = this.manager.find(SistemaOperacional.class, so.getId());
		this.manager.remove(soParaRemover);
	}
	
	public SistemaOperacional altera(SistemaOperacional so){
		return this.manager.merge(so);
	}
}
