package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.SoftwareDao;
import mb.dsam.modelo.Software;

@ViewScoped
@ManagedBean
public class SoftwareBean implements Serializable {
	@Inject
	private Software software;
	@Inject
	private SoftwareDao softwareDao;
	
	private List<Software> softwares;
	
	private List<Software> softwaresPadroes;
	
	private List<Software> softwaresNaoPadroes;
	
	
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	
	public List<Software> getSoftwares() {
		if (this.softwares == null){
			this.softwares = softwareDao.lista();
		}
		return softwares;
		
	}
	
	public void grava() {
		
		if (this.software.getId() == null) {
			
			softwareDao.adiciona(software);
			this.softwares = softwareDao.lista();
			this.softwaresPadroes = softwareDao.listaSoftwaresPadroes();
			this.softwaresNaoPadroes = softwareDao.listaSoftwaresNaoPadroes();
			limpaFormularioDoJSF();
		} else {
			softwareDao.altera(software);
			this.softwares =softwareDao.lista();
			this.softwaresPadroes = softwareDao.listaSoftwaresPadroes();
			this.softwaresNaoPadroes = softwareDao.listaSoftwaresNaoPadroes();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(Software software) {
		System.out.println("Removendo...");
		softwareDao.remove(software);
		this.softwares = softwareDao.lista();
		this.softwaresPadroes = softwareDao.listaSoftwaresPadroes();
		this.softwaresNaoPadroes = softwareDao.listaSoftwaresNaoPadroes();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.software = new Software();
	}
	
	public List<Software> getSoftwaresPadroes() {
		if (this.softwaresPadroes == null){
			this.softwaresPadroes = softwareDao.listaSoftwaresPadroes();
		}
		return softwaresPadroes;
		
	}
		
	
	public void setSoftwaresPadroes(List<Software> softwaresPadroes) {
		this.softwaresPadroes = softwaresPadroes;
	}
	public List<Software> getSoftwaresNaoPadroes() {
		if (this.softwaresNaoPadroes == null){
			this.softwaresNaoPadroes = softwareDao.listaSoftwaresNaoPadroes();
		}
		return softwaresNaoPadroes;
		
	}
	
	public void setSoftwaresNaoPadroes(List<Software> softwaresNaoPadroes) {
		this.softwaresNaoPadroes = softwaresNaoPadroes;
	}
	
}
