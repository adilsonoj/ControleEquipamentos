package mb.dsam.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.SistemaOperacional;

@Stateless
public class SistemaOperacionalDao implements Serializable{
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(SistemaOperacional so) {
		this.manager.persist(so);
	}

	public SistemaOperacional busca(Long id) {
		return this.manager.find(SistemaOperacional.class, id);
	}

	public List<SistemaOperacional> lista() {
		return this.manager.createQuery("select c from SistemaOperacional c", SistemaOperacional.class)
				.getResultList();
	}

	public void remove(SistemaOperacional so) {
		SistemaOperacional soParaRemover = this.manager.find(SistemaOperacional.class, so.getId());
		this.manager.remove(soParaRemover);
	}
	
	public SistemaOperacional altera(SistemaOperacional so){
		return this.manager.merge(so);
	}

	public SistemaOperacional buscaPorNome(String nome) {
		String jpql = "select s from SistemaOperacional s where s.nome = :nome";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("nome", nome);
		return (SistemaOperacional) query.getSingleResult();
		
	}
	
	
}
