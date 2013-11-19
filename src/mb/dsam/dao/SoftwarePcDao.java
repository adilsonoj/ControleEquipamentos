package mb.dsam.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.SoftwarePc;
@Stateless
public class SoftwarePcDao implements Serializable{
	@PersistenceContext
	EntityManager manager;

	public void adiciona(SoftwarePc softwarePc) {
		this.manager.persist(softwarePc);
	}

	public SoftwarePc busca(Long id) {
		return this.manager.find(SoftwarePc.class, id);
	}

	public ArrayList<SoftwarePc> lista() {
		return (ArrayList<SoftwarePc>) this.manager.createQuery("select m from SoftwarePc m", SoftwarePc.class)
				.getResultList();
	}

	public void remove(SoftwarePc softwarePc) {
		SoftwarePc softwarePcParaRemover = this.manager.find(SoftwarePc.class, softwarePc.getId());
		this.manager.remove(softwarePcParaRemover);
	}
	
	public SoftwarePc altera(SoftwarePc softwarePc){
		return this.manager.merge(softwarePc);
	}
	
}
