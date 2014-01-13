package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.ImpressoraDao;
import mb.dsam.modelo.Impressora;

@ViewScoped
@ManagedBean
public class ImpressoraBean implements Serializable {
	@Inject
	private Impressora impressora;
	@Inject
	private ImpressoraDao impressoraDao;
	
	private List<Impressora> impressoras;
	
	
	public Impressora getImpressora() {
		return impressora;
	}
	public void setImpressora(Impressora impressora) {
		this.impressora = impressora;
	}
	
	public List<Impressora> getImpressoras() {
		if (this.impressoras == null){
			this.impressoras = impressoraDao.lista();
		}
		return impressoras;
		
	}
	
	
	
	public void setImpressoras(List<Impressora> impressoras) {
		this.impressoras = impressoras;
	}
	
	public void grava() {
		
		if (this.impressora.getNumeroPatrimonial() == null) {
			
			impressoraDao.adiciona(impressora);
			this.impressoras = impressoraDao.lista();
			limpaFormularioDoJSF();
		} else {
			impressoraDao.altera(impressora);
			this.impressoras =impressoraDao.lista();
			limpaFormularioDoJSF();
		}
	}
	
	public void remove(Impressora impressora) {
		System.out.println("Removendo...");
		impressoraDao.remove(impressora);
		this.impressoras = impressoraDao.lista();
		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.impressora = new Impressora();
	}
}
