package mb.dsam.mb;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import mb.dsam.dao.EmprestimoDao;
import mb.dsam.dao.NotebookDao;
import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Notebook;

@RequestScoped
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
	
	Calendar data = Calendar.getInstance();
	
	public void grava() {
		Notebook notebookRelacionado = notebookDao.busca(notebookNumeroPatrimonial);
		emprestimo.setNotebook(notebookRelacionado);
		emprestimoDao.adiciona(emprestimo);
		this.emprestimos = emprestimoDao.lista();
		limpaFormularioDoJSF();
	}
	

	public void remove(Emprestimo emprestimo) {
		System.out.println("Removendo a Emprestimo");
		Notebook notebookRelacionado = notebookDao.busca(notebookNumeroPatrimonial);
		emprestimo.setNotebook(notebookRelacionado);
		emprestimoDao.remove(emprestimo);
		this.emprestimos=emprestimoDao.lista();
		limpaFormularioDoJSF();
	}
	
	public void fecha(Emprestimo emprestimo){
		emprestimo.setDataEntrada(Calendar.getInstance());
		emprestimoDao.fecha(emprestimo);
		this.emprestimos=emprestimoDao.lista();
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
	
	public List<Emprestimo> getListaNaoDevolvidos(){
		return emprestimoDao.listaNaoDevolvidos();
	}
	
	
}
