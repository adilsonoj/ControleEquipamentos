package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Notebook;
import mb.dsam.modelo.SistemaOperacional;

@ViewScoped
@ManagedBean
public class ChaveSerialBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ChaveSerial chaveSerial;
	
	@Inject
	ChaveSerialDao dao;
	
	@Inject
	SistemaOperacional sistemaOperacional;
	@Inject
	SistemaOperacionalDao soDao;
	
	private Integer sistemaOperacionalId;	
	

	private List<ChaveSerial> chaveSerials;

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}
	
	public void grava() {
		
		if (this.chaveSerial.getId() == null) {
			SistemaOperacional soRelacionado = soDao.busca(sistemaOperacionalId);
			chaveSerial.setSistemaOperacional(soRelacionado);
			
			dao.adiciona(chaveSerial);
			this.chaveSerials = dao.lista();
			limpaFormularioDoJSF();
		} else {
			SistemaOperacional soRelacionado = soDao.busca(sistemaOperacionalId);
			chaveSerial.setSistemaOperacional(soRelacionado);
			dao.altera(chaveSerial);
			this.chaveSerials =dao.lista();
			limpaFormularioDoJSF();
		}
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
	
	

	public Integer getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Integer sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 */
	private void limpaFormularioDoJSF() {
		this.chaveSerial = new ChaveSerial();
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}
}
