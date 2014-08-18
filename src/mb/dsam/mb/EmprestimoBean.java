package mb.dsam.mb;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.EmprestimoDao;
import mb.dsam.dao.NotebookDao;
import mb.dsam.modelo.Emprestimo;
import mb.dsam.modelo.Notebook;
import mb.dsam.modelo.Pc;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

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
	
	@Inject
	PcBean bean;
	@Inject
	Pc pco;
	
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
	
	
	
	
	private void limpaFormularioDoJSF() {
		this.emprestimo = new Emprestimo();
	}
	
	public List<Emprestimo> getListaNaoDevolvidos(){
		return emprestimoDao.listaNaoDevolvidos();
	}
	
	public List<Emprestimo> getListaPorNotebook(){
		
		return emprestimoDao.emprestimoPorNotebook(notebookNumeroPatrimonial);
	}
	
	public void importar() {
		
		
        Document doc = null;

        SAXBuilder builder = new SAXBuilder();
        try {
              doc = builder.build("d:/temp/pc.xml");
             System.out.println(doc.getDocType());

        } catch (Exception e) {

              e.printStackTrace();
        }           

        Element pc = doc.getRootElement();

        List<Element> lista = pc.getChildren();

        for (Element e: lista ){
        	
        	 System.out.println("pc: "+ e.getAttributeValue("id"));
              System.out.println("Nome: " + e.getChildText("nome"));
              System.out.println("numeroPatrimonial: " + e.getChildText("numeroPatrimonial"));
              System.out.println("ip: " + e.getChildText("ip"));
              System.out.println("macAdress: " + e.getChildText("macAdress"));
              
        		pco.setNome(e.getChildText("nome"));
        		pco.setNumeroPatrimonial((long)123456789);
        		pco.setIp(e.getChildText("ip"));
        		
        		bean.setPc(pco);
        		bean.grava();
        		System.out.println(pco.getNome());                

        }           

  }

	
}
