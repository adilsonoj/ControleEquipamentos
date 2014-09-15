package mb.dsam.util;

import javax.inject.Inject;

import mb.dsam.dao.ProcessadorDao;
import mb.dsam.modelo.Processador;

public class VerificaProcessador {
	
	private String nomeMarca;
	private String nomeModelo;
	@Inject
	Processador processadorAux;
	@Inject
	ProcessadorDao processadorDao;
	
	public void verificaProcessador(String nomeProcessador){
		
		if (nomeProcessador.contains("Intel")) {
			nomeMarca = "Intel";
			processadorAux.setMarca(nomeMarca);
			nomeModelo = nomeProcessador.replaceAll("Intel|CPU|.R.|.TM.|.tm.|@|Processor", "");
			processadorAux.setModelo(nomeModelo);

		} else if(nomeProcessador.contains("AMD")){
			nomeMarca = "AMD";
			processadorAux.setMarca(nomeMarca);
			nomeModelo = nomeProcessador.replaceAll("AMD|CPU|.R.|.TM.|.tm.|@|Processor", "");
			processadorAux.setModelo(nomeModelo);
			
		} else{
			nomeMarca = "Null";
			processadorAux.setMarca(nomeMarca);
			nomeModelo = "Null";
			processadorAux.setModelo(nomeModelo);
		}
		
		try {
			processadorDao.buscaPorNome(nomeMarca, nomeModelo);
		} catch (Exception e2) {
			processadorDao.altera(processadorAux);
			System.out.println(processadorAux.getMarca());
		}
		
		
}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public String getNomeModelo() {
		return nomeModelo;
	}
}
