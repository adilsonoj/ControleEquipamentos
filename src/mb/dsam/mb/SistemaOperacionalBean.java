package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.modelo.TipoSistemaOperacional;

@SessionScoped
@Named
public class SistemaOperacionalBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	SistemaOperacional sistemaOperacional;
	@Inject
	SistemaOperacionalDao dao;
	@Inject
	Pc pc;
	
	private List<SistemaOperacional> sistemasOperacionais;

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSo(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public List<SistemaOperacional> getSistemasOperacionais() {
		if (this.sistemasOperacionais == null){
			this.sistemasOperacionais = dao.lista();
		}
		return sistemasOperacionais;
	}
	
	public void grava() {
		
		if (this.sistemaOperacional.getId() == null) {
			
			dao.adiciona(sistemaOperacional);
			this.sistemasOperacionais = dao.lista();
			limpaFormularioDoJSF();
		} else {
			dao.altera(sistemaOperacional);
			this.sistemasOperacionais =dao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(SistemaOperacional so) {
		System.out.println("Removendo...");
		dao.remove(so);
		this.sistemasOperacionais = dao.lista();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.sistemaOperacional = new SistemaOperacional();
	}
	
	public TipoSistemaOperacional[] getTiposSistemaOperacional() {
		return TipoSistemaOperacional.values();
	}
	
}
