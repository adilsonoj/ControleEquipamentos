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
			limpaFormularioDoJSF();
		} else {
			softwareDao.altera(software);
			this.softwares =softwareDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(Software software) {
		System.out.println("Removendo...");
		softwareDao.remove(software);
		this.softwares = softwareDao.lista();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.software = new Software();
	}
	
}
