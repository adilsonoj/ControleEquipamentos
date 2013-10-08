package mb.dsam.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.EmprestimoDao;
import mb.dsam.dao.NotebookDao;
import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Notebook;

@ViewScoped
@ManagedBean
public class EmprestimoBean {
	
	@Inject
	Emprestimo emprestimo;
	
	@Inject
	Notebook notebook;
	private List<Emprestimo> emprestimos;
	private Integer notebookNumeroPatrimonial;
	
	@Inject
	EmprestimoDao emprestimoDao;
	@Inject
	NotebookDao notebookDao;
	
	
	public void grava() {
		Notebook contaRelacionada = notebookDao.busca(notebookNumeroPatrimonial);
		emprestimo.setNotebook(contaRelacionada);
		emprestimoDao.adiciona(emprestimo);
		this.emprestimos = emprestimoDao.lista();
		limpaFormularioDoJSF();
	}
	

	public void remove(Emprestimo emprestimo) {
		System.out.println("Removendo a Emprestimo");
		Notebook notebookRelacionada = notebookDao.busca(notebookNumeroPatrimonial);
		emprestimo.setNotebook(notebookRelacionada);
		emprestimoDao.remove(emprestimo);
		this.emprestimos=emprestimoDao.lista();
		limpaFormularioDoJSF();
	}
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
	
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	
	public Integer getNotebookNumeroPatrimonial() {
		return notebookNumeroPatrimonial;
	}
	
	public void setNotebookNumeroPatrimonial(Integer notebookNumeroPatrimonial) {
		this.notebookNumeroPatrimonial = notebookNumeroPatrimonial;
	}
	
	
	public void listaEmprestimosDoNotebook() {
		emprestimos = emprestimoDao.listaTodosEmprestimos(notebook);
	}
	
	private void limpaFormularioDoJSF() {
		this.emprestimo = new Emprestimo();
	}
	
}
