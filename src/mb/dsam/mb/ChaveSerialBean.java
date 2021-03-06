package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.SistemaOperacional;

import org.primefaces.event.ToggleEvent;

@ViewScoped
@ManagedBean
public class ChaveSerialBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ChaveSerial chaveSerial;
	
	@Inject
	private ChaveSerialDao dao;
	
	@Inject
	private SistemaOperacional sistemaOperacional;
	@Inject
	private SistemaOperacionalDao soDao;
	@Inject 
	private Pc pc;

	private Long sistemaOperacionalId;	
	
	private List<ChaveSerial> chaveSerials;

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}
	
	public void grava() {
		
		if (this.chaveSerial.getId() == null) {
			SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
			this.chaveSerial.setSistemaOperacional(soRelacionado);
			
			this.dao.altera(this.chaveSerial);
			this.chaveSerials = this.dao.lista();
			limpaFormularioDoJSF();
		} else {
			SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
			this.chaveSerial.setSistemaOperacional(soRelacionado);
			dao.altera(chaveSerial);
			chaveSerials = dao.lista();
			limpaFormularioDoJSF();
		}
	}

	public void altera(){
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);
		
		dao.altera(chaveSerial);
		chaveSerials = dao.lista();
		limpaFormularioDoJSF();
	}
	
	public List<ChaveSerial> getChaveSerials() {
		if (this.chaveSerials == null){
			this.chaveSerials = dao.lista();
		}
		return chaveSerials;
	}
	
	public void remove(ChaveSerial chaveSerial) {
		System.out.println("Removendo...");
		dao.remove(chaveSerial);
		this.chaveSerials = dao.lista();
		limpaFormularioDoJSF();
	}
	
	

	public Long getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Long sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 */
	private void limpaFormularioDoJSF() {
		this.chaveSerial = new ChaveSerial();
		this.sistemaOperacional = new SistemaOperacional();
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}
	
	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}
	
	public List<ChaveSerial> getListaPorSo(){
		return dao.listaPorSo(this.sistemaOperacionalId);
	}
	
	public List<ChaveSerial> getListaNotebookPorSo(){
		return dao.listaNotebookPorSo(this.sistemaOperacionalId);
	}
	
	public void onRowToggle(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                                            "Row State " + event.getVisibility(),  
                                            "PC:" + ((ChaveSerial) event.getData()).getPc());  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	
}
