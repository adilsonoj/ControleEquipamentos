package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.ChaveSerial;


@Stateless
public class ChaveSerialDao {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(ChaveSerial chaveSerial) {
		this.manager.persist(chaveSerial);
	}

	public ChaveSerial busca(Long numeroPatrimonial) {
		return this.manager.find(ChaveSerial.class, numeroPatrimonial);
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
