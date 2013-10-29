package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	

	public void remove(Pc pc) {
		Pc pcParaRemover = this.manager.find(Pc.class, pc.getNumeroPatrimonial());
		this.manager.remove(pcParaRemover);
	}
	
	public Pc altera(Pc pc){
		return this.manager.merge(pc);
	}
}
