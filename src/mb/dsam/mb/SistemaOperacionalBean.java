package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.SistemaOperacional;


@ViewScoped
@ManagedBean
public class SistemaOperacionalBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	SistemaOperacional sistemaOperacional;
	@Inject
	SistemaOperacionalDao dao;
	
	
	private List<SistemaOperacional> sistemasOperacionais;

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
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
	
	public void adicionaSO() {
		try {
			dao.buscaPorNome(this.sistemaOperacional.getNome());
			//dao.busca(this.sistemaOperacional.getId());
		} catch (Exception e) {
			dao.altera(this.sistemaOperacional);
		}
		
		
		
	}
	
	public void limpaFormularioDoJSF() {
		this.sistemaOperacional = new SistemaOperacional();
	}



	public SistemaOperacional buscaPorNome(String nomeSO) {
		
		return this.dao.buscaPorNome(nomeSO);
	}



	
	
	
	
	
	
}
