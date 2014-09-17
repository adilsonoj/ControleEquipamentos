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
	@Inject
	MemoriaDao dao;

	public Memoria getMemoria() {
		return memoria;
	}

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
			int tamanhoEmGb = (tamanhoEmMb / 1024 +1);

			this.setTamanho(Integer.toString(tamanhoEmGb), "GB");
			memoria.setTamanho(this.tamanho);
			memoria.setModelo("nulo");
			
		} else {
			this.setTamanho(Integer.toString(tamanhoEmMb), "MB");
			memoria.setTamanho(this.tamanho);
			memoria.setModelo("nulo");
		
			}
				System.out.println("entrando busca memoria");
						try {						
							dao.buscaPorNome("nulo", tamanho);
						} catch (Exception e) {
							try {
								dao.altera(memoria);
							} catch (Exception e2) {
								System.out.println("erro alterar"+e.getMessage());
							}
							
						}
						
	}
}
