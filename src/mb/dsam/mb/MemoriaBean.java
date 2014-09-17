package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.MemoriaDao;
import mb.dsam.modelo.Memoria;

@ViewScoped
@ManagedBean
public class MemoriaBean implements Serializable{
	@Inject
	private Memoria memoria;
	@Inject
	private MemoriaDao memoriaDao;
	
	private List<Memoria> memorias;
	
	
	public Memoria getMemoria() {
		return memoria;
	}
	public void setMemoria(Memoria memoria) {
		this.memoria = memoria;
	}
	
	public List<Memoria> getMemorias() {
		if (this.memorias == null){
			this.memorias = memoriaDao.lista();
		}
		return memorias;
		
	}
	
	public void grava() {
		
		if (this.memoria.getId() == null) {
			
			memoriaDao.adiciona(memoria);
			this.memorias = memoriaDao.lista();
			limpaFormularioDoJSF();
		} else {
			memoriaDao.altera(memoria);
			this.memorias =memoriaDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(Memoria memoria) {
		System.out.println("Removendo...");
		memoriaDao.remove(memoria);
		this.memorias = memoriaDao.lista();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.memoria = new Memoria();
	}
	
	
		
	
	
	
}
