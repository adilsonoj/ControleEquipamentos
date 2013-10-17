package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.PcDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Pc;

@SessionScoped
@ManagedBean
public class PcBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pc pc;
	
	@Inject
	private PcDao pcDao;
	@Inject
	private ChaveSerialDao serialDao;
	
	@Inject
	private ChaveSerial chaveSerial;
	private List<Pc> pcs;
	@Inject
	private ChaveSerialBean chaveSerialBean;
	

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}
	
	public void grava() {
		if (this.pc.getNumeroPatrimonial() == null) {
			chaveSerialBean.grava();
			pcDao.adiciona(pc);
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		} else {
			pcDao.altera(pc);
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		}
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
	
	
	
}
