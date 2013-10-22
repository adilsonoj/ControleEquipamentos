package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.PcDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.SistemaOperacional;

@SessionScoped
@ManagedBean
public class PcBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pc pc;
	
	@Inject
	private PcDao pcDao;
	
	@Inject
	private ChaveSerial chaveSerial;
	private List<Pc> pcs;
	@Inject
	private ChaveSerialDao chaveSerialDao;
	
	@Inject
	private SistemaOperacionalDao soDao;
	
	private Integer sistemaOperacionalId;
	
	private Integer serialId;

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}
	
	public void grava() {
		if (this.pc.getNumeroPatrimonial() == null) {
			
			gravaChaveSerial();
			
			ChaveSerial chaveRelacionada = chaveSerialDao.busca(this.chaveSerial.getId());
			this.pc.setChaveSerial(chaveRelacionada);
			
			
			pcDao.adiciona(pc);
			
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		} else {
			gravaChaveSerial();
			
			ChaveSerial chaveRelacionada = chaveSerialDao.busca(this.chaveSerial.getId());
			this.pc.setChaveSerial(chaveRelacionada);
			
			
			pcDao.altera(pc);
			
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	private void gravaChaveSerial(){
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);
		this.chaveSerialDao.adiciona(chaveSerial);
		
	}

	
	public Integer getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Integer sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	public List<Pc> getPcs() {
		System.out.println("Listando as pcs");
		if (this.pcs == null) {
			this.pcs = pcDao.lista();
		} 
		return this.pcs;
	}

	public void remove(Pc pc) {
		System.out.println("Removendo a pc");
		pcDao.remove(pc);
		this.pcs=pcDao.lista();
		limpaFormularioDoJSF();
	}
	
	
	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.pc = new Pc();
	}

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}
	
	
	
}
