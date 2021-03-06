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
		
			
		public void fecha(Emprestimo emprestimo){
			this.manager.merge(emprestimo);
		}
		
		public List<Emprestimo> listaNaoDevolvidos(){
			return this.manager.createQuery("Select m from Emprestimo m where dataentrada is null", Emprestimo.class).getResultList();
		}
		
		public Emprestimo buscaPorNP(Integer notebook_numeropatrimonial) {
			return this.manager.find(Emprestimo.class, notebook_numeropatrimonial);
		}
		
		public List<Emprestimo> emprestimoPorNotebook(Integer notebook_numeropatrimonial){
			String jpql = "Select m from Emprestimo m where m.notebook.numeroPatrimonial = :notebook_numeropatrimonial";
			Query query = this.manager.createQuery(jpql);
			query.setParameter("notebook_numeropatrimonial", notebook_numeropatrimonial);
			return query.getResultList();
		}
		
}
