package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.PC;

@Stateless
public class PcDao {
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(PC pc) {
		this.manager.persist(pc);
	}

	public PC busca(Integer id) {
		return this.manager.find(PC.class, id);
	}

	public List<PC> lista() {
		return this.manager.createQuery("select c from PC c", PC.class)
				.getResultList();
	}

	public void remove(PC pc) {
		PC pcParaRemover = this.manager.find(PC.class, pc.getNumeroPatrimonial());
		this.manager.remove(pcParaRemover);
	}
	
	public PC altera(PC pc){
		return this.manager.merge(pc);
	}
}
