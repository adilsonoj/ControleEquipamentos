package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.NotebookDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Notebook;
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
		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.notebook.setMemoria(memoriaRelacionado);
		notebookDao.adiciona(notebook);
		
		Notebook notebookRelacionado = notebookDao.busca(notebook.getNumeroPatrimonial());
		this.chaveSerial.setNotebook(notebookRelacionado);
		
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);
		
		
		this.chaveSerialDao.adiciona(chaveSerial);
		
		this.chavesSeriais = chaveSerialDao.lista();
		this.notebooks = notebookDao.lista();
		
		limpaFormularioDoJSF();
	
}

public void altera(){
	Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
	this.notebook.setMemoria(memoriaRelacionado);
	notebookDao.altera(notebook);
	
	ChaveSerial chave = chaveSerialDao.buscaPorNotebook(notebook);
	
	
	SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
	chave.setSistemaOperacional(soRelacionado);
	chave.setChaveSerial(this.chaveSerial.getSerial());
	this.chaveSerialDao.altera(chave);
	
	this.chavesSeriais = chaveSerialDao.lista();
	this.notebooks = notebookDao.lista();
	limpaFormularioDoJSF();

}
	
	public void grava_old() {
		
		if (this.notebook.getNumeroPatrimonial() == null) {
			notebookDao.adiciona(notebook);
			this.notebooks = notebookDao.lista();
			limpaFormularioDoJSF();
		} else {
			notebookDao.altera(notebook);
			this.notebooks =notebookDao.lista();
			limpaFormularioDoJSF();
		}
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
	
}
