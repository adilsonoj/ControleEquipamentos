package mb.dsam.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.NotebookDao;
import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Notebook;

@ViewScoped
@ManagedBean
public class NotebookBean {
	
	@Inject
	Notebook notebook;
	
	@Inject
	NotebookDao dao;
	
	

	private List<Notebook> notebooks;

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	public void grava() {
		
		if (this.notebook.getNumeroPatrimonial() == null) {
			dao.adiciona(notebook);
			this.notebooks = dao.lista();
			limpaFormularioDoJSF();
		} else {
			dao.altera(notebook);
			this.notebooks =dao.lista();
			limpaFormularioDoJSF();
		}
	}

	public List<Notebook> getNotebooks() {
		if (this.notebooks == null){
			this.notebooks = dao.lista();
		}
		return notebooks;
	}
	
	public void remove(Notebook notebook) {
		System.out.println("Removendo...");
		dao.remove(notebook);
		this.notebooks = dao.lista();
		limpaFormularioDoJSF();
	}
	
	

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 */
	private void limpaFormularioDoJSF() {
		this.notebook = new Notebook();
	}
	
	
	
}
