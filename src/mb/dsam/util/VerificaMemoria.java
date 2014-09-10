package mb.dsam.util;

import javax.inject.Inject;

import mb.dsam.dao.MemoriaDao;
import mb.dsam.mb.MemoriaBean;
import mb.dsam.modelo.Memoria;

public class VerificaMemoria {
	@Inject
	Memoria memoria;
	String tamanho;
	@Inject
	MemoriaBean bean;

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho, String UN) {
		this.tamanho = tamanho + UN;
	}

	public void converteMemoria(String tamanho) {
		tamanho = tamanho.replaceAll("MB", "").trim();
		int tamanhoEmMb = Integer.parseInt(tamanho);

		if (tamanhoEmMb >= 1024) {
			int tamanhoEmGb = (tamanhoEmMb / 1024);

			this.setTamanho(Integer.toString(tamanhoEmGb), "GB");
			memoria.setTamanho(getTamanho());
			bean.setMemoria(memoria);
			bean.adicionaMemoria();

		} else {
			this.setTamanho(Integer.toString(tamanhoEmMb), "MB");
			memoria.setTamanho(getTamanho());
			bean.setMemoria(memoria);
			bean.adicionaMemoria();
		}

	}

}
