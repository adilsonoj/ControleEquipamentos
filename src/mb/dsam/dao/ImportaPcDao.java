package mb.dsam.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.ImportaPc;


@Stateless
public class ImportaPcDao {
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(ImportaPc importaPc) {
		this.manager.persist(importaPc);
	}

	public ImportaPc busca(Long id) {
		return this.manager.find(ImportaPc.class, id);
	}
	
	

	public List<ImportaPc> lista() {
		return this.manager.createQuery("select c from ImportaPc c", ImportaPc.class)
				.getResultList();
	}
	
	public List<ImportaPc> listaComChave(){
		return this.manager.createQuery("select p from ImportaPc p left join fetch p.chaveSerial", ImportaPc.class).getResultList();
	}
	
	public List<ImportaPc> listaIps(){
		return this.manager.createQuery("select p.ip from ImportaPc p", ImportaPc.class).getResultList();
	}
	

	public void remove(ImportaPc pc) {
		ImportaPc importaPcParaRemover = this.manager.find(ImportaPc.class, pc.getNumeroPatrimonial());
		this.manager.remove(importaPcParaRemover);
	}
	
	public ImportaPc altera(ImportaPc importaPc){
		return this.manager.merge(importaPc);
	}
	
	public List<ImportaPc> buscaPorNp(Long numeroPatrimonial){
		String jpql = "select p from ImportaPc p where p.numeroPatrimonial = :numeroPatrimonial";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("numeroPatrimonial", numeroPatrimonial);
		
		return query.getResultList();
	}

	public List<ImportaPc> buscaPorIp(String ip) {
		String jpql = "select p from ImportaPc p where p.ip = :ip";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("ip", ip);
		
		return query.getResultList();
	}
	
	
	public List<ImportaPc> buscaPorNome(String nome) {
		String jpql = "select p from ImportaPc p where p.nome = :nome";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("nome", nome);
		
		return query.getResultList();
	} 
	
	
	
	
}
