package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.ProcessadorDao;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Processador;

@ViewScoped
@ManagedBean
public class ProcessadorBean implements Serializable {
	@Inject
	private Processador processador;
	@Inject
	private ProcessadorDao processadorDao;
		
	private List<Processador> processadores;

	public Processador getProcessador() {
		return processador;
	}

	public ProcessadorDao getProcessadorDao() {
		return processadorDao;
	}

	public List<Processador> getProcessadores() {
		
		if (this.processadores == null){
			this.processadores = processadorDao.lista();
		}
		
		return processadores;
	}
		

public void grava() {
		
		if (this.processador.getId() == null) {
			
			processadorDao.adiciona(processador);
			this.processadores = processadorDao.lista();
			limpaFormularioDoJSF();
		} else {
			processadorDao.altera(processador);
			this.processadores =processadorDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(Processador processador) {
		System.out.println("Removendo...");
		processadorDao.remove(processador);
		this.processadores = processadorDao.lista();
		limpaFormularioDoJSF();
	}
	
	public void setProcessador(Processador processador) {
		this.processador = processador;
	}

	private void limpaFormularioDoJSF() {
		this.processador = new Processador();
	}
	
}
