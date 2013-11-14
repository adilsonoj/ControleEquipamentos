package mb.dsam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import mb.dsam.dao.PcDao;
import mb.dsam.dao.SoftwareDao;
import mb.dsam.dao.SoftwarePcDao;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Software;
import mb.dsam.modelo.SoftwarePc;
@ViewScoped
@ManagedBean
public class SoftwarePcBean implements Serializable {
	@Inject
	private SoftwarePc softwarePc;
	@Inject
	private SoftwarePcDao softwarePcDao;
	@Inject
	private PcDao pcDao;
	@Inject
	private SoftwareDao softwareDao;
	
	private List<Software> softwares = new ArrayList<Software>();
	
	private List<Software> softwaresTodos = new ArrayList<Software>();
	private List<Software> softwaresSelecionados = new ArrayList<Software>();
	
	private DualListModel<Software> software = new DualListModel<Software>(softwaresTodos, softwaresSelecionados);
	
	private Long numeroPatrimonial;
	private Long softwareId;
	
	
	
	public Long getNumeroPatrimonial() {
		return numeroPatrimonial;
	}
	public void setNumeroPatrimonial(Long numeroPatrimonial) {
		this.numeroPatrimonial = numeroPatrimonial;
	}
	public Long getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(Long softwareId) {
		this.softwareId = softwareId;
	}

	private List<SoftwarePc> softwarePcs;
	
	
	public SoftwarePc getSoftwarePc() {
		return softwarePc;
	}
	public void setSoftwarePc(SoftwarePc softwarePc) {
		this.softwarePc = softwarePc;
	}
	
	public List<SoftwarePc> getSoftwarePcs() {
		if (this.softwarePcs == null){
			this.softwarePcs = softwarePcDao.lista();
		}
		return softwarePcs;
		
	}
	
	public void grava() {
		
			for (int i=0; i < this.getSoftwaresTodos().size(); i++){
				Pc pcRelacionado = pcDao.busca(this.numeroPatrimonial);
				this.softwarePc.setPc(pcRelacionado);
			
				this.softwarePc.setSoftware(this.getSoftwaresTodos().get(i));
				softwarePcDao.altera(softwarePc);	
			}
			limpaFormularioDoJSF();
	}
	
	public void guardaItem(){
	
		Software softwareRelacionado = softwareDao.busca(this.softwareId);
		
		this.getSoftwares().add(softwareRelacionado);
			
	}
	
	public void remove(SoftwarePc softwarePc) {
		System.out.println("Removendo...");
		softwarePcDao.remove(softwarePc);
		this.softwarePcs = softwarePcDao.lista();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.setNumeroPatrimonial(null);
		this.setNumeroPatrimonial(null);
		this.setSoftwares(null);
		
	}
	
	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}
	public List<Software> getSoftwaresTodos() {
		for (int i=0; i < softwareDao.lista().size(); i++){
		this.softwaresTodos.add(softwareDao.lista().get(i));
		
		}
		return softwaresTodos;
	}
	
	public List<Software> getSoftwaresSelecionados() {
		return softwaresSelecionados;
	}
	public void setSoftwaresSelecionados(List<Software> softwaresSelecionados) {
		this.softwaresSelecionados = softwaresSelecionados;
	}
	public DualListModel<Software> getSoftware() {
		getSoftwaresTodos();
		return software;
	}
	public void setSoftware(DualListModel<Software> software) {
		this.software = software;
	}

}
