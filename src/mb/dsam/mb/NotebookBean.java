package mb.dsam.mb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mb.dsam.dao.NotebookDao;
import mb.dsam.modelo.Notebook;

@Stateless
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
			System.out.println("Gravando a notebook");
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
	
	public void remove() {
		System.out.println("Removendo...");
		dao.remove(this.notebook);
		this.notebooks = dao.lista();
		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.notebook = new Notebook();
	}
	
	
	
}
