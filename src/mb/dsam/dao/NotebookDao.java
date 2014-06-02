package mb.dsam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mb.dsam.modelo.Notebook;

@Stateless
public class NotebookDao {
	
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Notebook notebook) {
		this.manager.persist(notebook);
	}

	public Notebook busca(Integer numeroPatrimonial) {
		return this.manager.find(Notebook.class, numeroPatrimonial);
	}

	public List<Notebook> lista() {
		return this.manager.createQuery("select c from Notebook c", Notebook.class)
				.getResultList();
	}

	public void remove(Notebook notebook) {
		Notebook notebookParaRemover = this.manager.find(Notebook.class, notebook.getNumeroPatrimonial());
		this.manager.remove(notebookParaRemover);
	}
	
	public Notebook altera(Notebook notebook){
		return this.manager.merge(notebook);
	}
	
	
}
