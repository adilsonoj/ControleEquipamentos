package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.PcDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.modelo.TipoSistemaOperacional;

@SessionScoped
@ManagedBean
public class PcBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pc pc;
	@Inject
	private SistemaOperacional so;
	
	@Inject
	private PcDao pcDao;
	@Inject
	private SistemaOperacionalDao soDao;
	
	private List<Pc> pcs;
	

	
	public SistemaOperacional getSo() {
		return so;
	}

	public void setSo(SistemaOperacional so) {
		this.so = so;
	}

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}
	
	public void grava() {
		if (this.pc.getNumeroPatrimonial() == null) {
			
				pcDao.adiciona(pc);
				gravaSO();
				this.pcs = pcDao.lista();
				limpaFormularioDoJSF();
			
			
		} else {
			pcDao.altera(pc);
			alteraSO();
			this.pcs = pcDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	private void gravaSO(){
		pc = pcDao.busca(this.pc.getNumeroPatrimonial());
		so.setPc(pc);
		soDao.adiciona(so);
	}
	private void alteraSO(){
		pc = pcDao.busca(this.pc.getNumeroPatrimonial());
		so.setPc(pc);
		soDao.altera(so);
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
	
	public TipoSistemaOperacional[] getTiposSistemaOperacional() {
		return TipoSistemaOperacional.values();
	}
	
}
