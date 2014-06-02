package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.ChaveSerial;


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
	
	public ChaveSerial buscaPorPc(Long numeroPatrimonial){
		String jpql = "select c from ChaveSerial c where c.pc.numeroPatrimonial = :numeroPatrimonial";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("numeroPatrimonial", numeroPatrimonial);
		
		return (ChaveSerial) query.getSingleResult();
	}
	
	public ChaveSerial buscaPorNotebook(Integer numeroPatrimonial){
		String jpql = "select c from ChaveSerial c where c.notebook.numeroPatrimonial = :numeroPatrimonial";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("numeroPatrimonial", numeroPatrimonial);
		
		return (ChaveSerial) query.getSingleResult();
	}

	public List<ChaveSerial> lista() {
		return this.manager.createQuery("select c from ChaveSerial c", ChaveSerial.class)
				.getResultList();
	}
	
		
	public List<ChaveSerial> listaPorSo(Long sistemaoperacional_id){
		String jpql = "select c from ChaveSerial c where c.sistemaOperacional.id = :sistemaoperacional_id and pc_numeropatrimonial != null";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("sistemaoperacional_id", sistemaoperacional_id);
		return query.getResultList();
	}
	
	public List<ChaveSerial> listaNotebookPorSo(Long sistemaoperacional_id){
		String jpql = "select c from ChaveSerial c where c.sistemaOperacional.id = :sistemaoperacional_id and notebook_numeropatrimonial != null";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("sistemaoperacional_id", sistemaoperacional_id);
		return query.getResultList();
	}

	public void remove(ChaveSerial chaveSerial) {
		ChaveSerial chaveSerialParaRemover = this.manager.find(ChaveSerial.class, chaveSerial.getId());
		this.manager.remove(chaveSerialParaRemover);
	}
	
	public ChaveSerial altera(ChaveSerial chaveSerial){
		return this.manager.merge(chaveSerial);
	}

	
	
	
}
