package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Notebook;

@Stateless
public class EmprestimoDao {
	
		@PersistenceContext
		EntityManager manager;
		
		public void adiciona(Emprestimo emprestimo) {
			this.manager.persist(emprestimo);
		}

		public Emprestimo busca(Integer id) {
			return this.manager.find(Emprestimo.class, id);
		}

		public List<Emprestimo> lista() {
			return this.manager.createQuery("select m from Emprestimo m", Emprestimo.class).getResultList();
		}

		public void remove(Emprestimo emprestimo) {
			Emprestimo emprestimoParaRemover = this.manager.find(Emprestimo.class, emprestimo.getId());
			this.manager.remove(emprestimoParaRemover);
		}
		
		public List<Emprestimo> listaTodosEmprestimos(Notebook notebook){
			String jpql = "Select m from Emprestimo m " + "where m.notebook = :notebook order by m.valor desc";
			Query query = this.manager.createQuery(jpql);
			query.setParameter("notebook", notebook);
			return query.getResultList();
		}
		
		
}
