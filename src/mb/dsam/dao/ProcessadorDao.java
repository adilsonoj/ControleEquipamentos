package mb.dsam.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;
@Stateless
public class ProcessadorDao implements Serializable {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Processador processador) {
		this.manager.persist(processador);
	}

	public Processador busca(Long id) {
		return this.manager.find(Processador.class, id);
	}

	public List<Processador> lista() {
		return this.manager.createQuery("select m from Processador m", Processador.class)
				.getResultList();
	}
	
	
	public void remove(Processador processador) {
		Processador processadorParaRemover = this.manager.find(Processador.class, processador.getId());
		this.manager.remove(processadorParaRemover);
	}
	
	public Processador altera(Processador processador){
		return this.manager.merge(processador);
	}

	public Processador buscaPorNome(String marca, String modelo) {
		String jpql = "select p from Processador p where p.marca = :marca and p.modelo = :modelo";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("marca", marca);
		query.setParameter("modelo", modelo);
		return (Processador) query.getSingleResult();
		
	}
	
}
