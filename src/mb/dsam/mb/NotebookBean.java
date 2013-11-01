package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.NotebookDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Notebook;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.modelo.TipoSistemaOperacional;

@ViewScoped
@ManagedBean
public class NotebookBean implements Serializable{
	
	@Inject
	Notebook notebook;
	@Inject
	NotebookDao notebookDao;
	@Inject
	private ChaveSerial chaveSerial;
	@Inject
	private ChaveSerialDao chaveSerialDao;
	@Inject
	private SistemaOperacionalDao soDao;
	@Inject
	private MemoriaDao memoriaDao;
	@Inject
	private Memoria memoria;
	@Inject
	private Processador processador;
	@Inject
	private ProcessadorDao processadorDao;
	
	
	private Long processadorId;
	
	

	public Memoria getMemoria() {
		return memoria;
	}

	private Long memoriaId;
	
	private Long sistemaOperacionalId;

	private List<Notebook> notebooks;
	
	private List<ChaveSerial> chavesSeriais;

	
	
	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	
	
	public void grava() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		Processador processadorRelacionado = processadorDao.busca(this.processadorId);
		this.notebook.setProcessador(processadorRelacionado);
		
		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.notebook.setMemoria(memoriaRelacionado);
		notebookDao.adiciona(notebook);
		
		Notebook notebookRelacionado = notebookDao.busca(notebook.getNumeroPatrimonial());
		this.chaveSerial.setNotebook(notebookRelacionado);
		
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);
		
		try {
			this.chaveSerialDao.adiciona(chaveSerial);
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Esse Serial já existe!", null));
		}
		
		
		this.chavesSeriais = chaveSerialDao.lista();
		this.notebooks = notebookDao.lista();
		
		limpaFormularioDoJSF();
	
}

public void altera(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	Processador processadorRelacionado = processadorDao.busca(this.processadorId);
	this.notebook.setProcessador(processadorRelacionado);
	
	Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
	this.notebook.setMemoria(memoriaRelacionado);
	notebookDao.altera(notebook);
	
	ChaveSerial chave = chaveSerialDao.buscaPorNotebook(notebook.getNumeroPatrimonial());
	
	
	SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
	chave.setSistemaOperacional(soRelacionado);
	chave.setChaveSerial(this.chaveSerial.getSerial());
	try {
		this.chaveSerialDao.altera(chave);
	} catch (Exception e) {
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Esse Serial já existe!", null));
	}
	
	
	this.chavesSeriais = chaveSerialDao.lista();
	this.notebooks = notebookDao.lista();
	limpaFormularioDoJSF();

}
	
	

	public List<Notebook> getNotebooks() {
		if (this.notebooks == null){
			this.notebooks = notebookDao.lista();
		}
		return notebooks;
	}
	
	public void remove(Notebook notebook) {
		System.out.println("Removendo...");
		notebookDao.remove(notebook);
		this.notebooks = notebookDao.lista();
		limpaFormularioDoJSF();
	}
	
	

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 */
	private void limpaFormularioDoJSF() {
		this.notebook = new Notebook();
		this.chaveSerial = new ChaveSerial();
	
	}
	
	public TipoSistemaOperacional[] getTiposSistemasOperacionais() {
		return TipoSistemaOperacional.values();
	}

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}

	public Long getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Long sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	public List<ChaveSerial> getChavesSeriais() {
		return chavesSeriais;
	}

	public void setChavesSeriais(List<ChaveSerial> chavesSeriais) {
		this.chavesSeriais = chavesSeriais;
	}

	public Long getMemoriaId() {
		return memoriaId;
	}

	public void setMemoriaId(Long memoriaId) {
		this.memoriaId = memoriaId;
	}
	public Processador getProcessador() {
		return processador;
	}

	public void setProcessador(Processador processador) {
		this.processador = processador;
	}

	public Long getProcessadorId() {
		return processadorId;
	}

	public void setProcessadorId(Long processadorId) {
		this.processadorId = processadorId;
	}
	
}
