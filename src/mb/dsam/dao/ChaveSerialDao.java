package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Notebook;
import mb.dsam.modelo.Pc;


@Stateless
public class ChaveSerialDao {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(ChaveSerial chaveSerial) {
		this.manager.persist(chaveSerial);
	}

	public ChaveSerial busca(Long id) {
		return this.manager.find(ChaveSerial.class, id);
	}
	
	public ChaveSerial buscaPorPc(Pc pc){
		return this.manager.createQuery("select c from ChaveSerial c where c.pc = pc", ChaveSerial.class)
				.getSingleResult();
	}
	
	public ChaveSerial buscaPorNotebook(Notebook notebook){
		return this.manager.createQuery("select c from ChaveSerial c where c.notebook = notebook", ChaveSerial.class)
				.getSingleResult();
	}

	public List<ChaveSerial> lista() {
		return this.manager.createQuery("select c from ChaveSerial c", ChaveSerial.class)
				.getResultList();
	}

	public void remove(ChaveSerial chaveSerial) {
		ChaveSerial chaveSerialParaRemover = this.manager.find(ChaveSerial.class, chaveSerial.getId());
		this.manager.remove(chaveSerialParaRemover);
	}
	
	public ChaveSerial altera(ChaveSerial chaveSerial){
		return this.manager.merge(chaveSerial);
	}

	
	
	
}
