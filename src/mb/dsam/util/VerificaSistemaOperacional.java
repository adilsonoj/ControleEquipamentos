package mb.dsam.util;

import javax.inject.Inject;

import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.mb.SistemaOperacionalBean;
import mb.dsam.modelo.SistemaOperacional;

public class VerificaSistemaOperacional {
	@Inject
	SistemaOperacionalBean soBean;
	@Inject
	SistemaOperacional So;
	
			public void verificaSistemaOperacional(String nomeSO){

					So.setNome(nomeSO);
					soBean.setSistemaOperacional(So);
					soBean.adicionaSO();
	
			}
	}
	
	


