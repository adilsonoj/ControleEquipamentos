package mb.dsam.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Software;
@Stateless
public class SoftwareDao implements Serializable{
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Software software) {
		this.manager.persist(software);
	}

	public Software busca(Long id) {
		return this.manager.find(Software.class, id);
	}

	public List<Software> lista() {
		return this.manager.createQuery("select m from Software m", Software.class)
				.getResultList();
	}
	
	public List<Software> listaSoftwaresPadroes() {
		return this.manager.createQuery("select s from Software s where s.padrao = true", Software.class)
				.getResultList();
	}
	

	
	public List<Software> listaSoftwaresNaoPadroes() {
		return this.manager.createQuery("select s from Software s where s.padrao = false", Software.class)
				.getResultList();
	}

	public void remove(Software software) {
		Software softwareParaRemover = this.manager.find(Software.class, software.getId());
		this.manager.remove(softwareParaRemover);
	}
	
	public Software altera(Software software){
		return this.manager.merge(software);
	}
	
	
	
}



