package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Pc;

@Stateless
public class PcDao {
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Pc pc) {
		this.manager.persist(pc);
	}

	public Pc busca(Long id) {
		return this.manager.find(Pc.class, id);
	}
	
	

	public List<Pc> lista() {
		return this.manager.createQuery("select c from Pc c", Pc.class)
				.getResultList();
	}
	
	public List<Pc> listaComChave(){
		return this.manager.createQuery("select p from Pc p left join fetch p.chaveSerial", Pc.class).getResultList();
	}
	
	public List<Pc> listaIps(){
		return this.manager.createQuery("select p.ip from Pc p", Pc.class).getResultList();
	}
	

	public void remove(Pc pc) {
		Pc pcParaRemover = this.manager.find(Pc.class, pc.getNumeroPatrimonial());
		this.manager.remove(pcParaRemover);
	}
	
	public Pc altera(Pc pc){
		return this.manager.merge(pc);
	}
	
	public List<Pc> buscaPorNp(Long numeroPatrimonial){
		String jpql = "select p from Pc p where p.numeroPatrimonial = :numeroPatrimonial";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("numeroPatrimonial", numeroPatrimonial);
		
		return query.getResultList();
	} 
	
	
}
