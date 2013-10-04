package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mb.dsam.dao.PcDao;
import mb.dsam.modelo.PC;

@ViewScoped
@ManagedBean
public class PcBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PC pc;
	
	@Inject
	private PcDao pcDao;
	
	private List<PC> pcs;

	public PC getPc() {
		return pc;
	}

	public void setPc(PC pc) {
		this.pc = pc;
	}
	
	public void grava() {
		if (this.pc.getNumeroPatrimonial() == null) {
			System.out.println("Gravando a pc");
			pcDao.adiciona(pc);
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		} else {
			pcDao.altera(pc);
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public List<PC> getPcs() {
		System.out.println("Listando as pcs");
		if (this.pcs == null) {
			this.pcs = pcDao.lista();
		} 
		return this.pcs;
	}

	public void remove() {
		System.out.println("Removendo a pc");
		pcDao.remove(this.pc);
		this.pcs=pcDao.lista();
		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.pc = new PC();
	}
}
