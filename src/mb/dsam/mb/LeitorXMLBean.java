package mb.dsam.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;

import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.ImportaPc;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.util.VerificaMacAdress;
import mb.dsam.util.VerificaMemoria;
import mb.dsam.util.VerificaProcessador;
import mb.dsam.util.VerificaSistemaOperacional;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

@ManagedBean(name = "lerXMLBean")
@ViewScoped
public class LeitorXMLBean implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Inject
	ImportaPcBean importaPcBean;
	@Inject
	ImportaPc importaPc;
	@Inject
	SistemaOperacionalBean soBean;
	@Inject
	SistemaOperacional so;
	@Inject
	ProcessadorDao processadorDao;
	@Inject
	Processador processador;
	@Inject
	Memoria memoria;
	@Inject
	MemoriaDao memoriaDao;
	@Inject
	VerificaProcessador verificaProcessador;
	@Inject
	VerificaSistemaOperacional verificaSO;
	@Inject
	VerificaMemoria verificaMemoria;
	@Inject
	VerificaMacAdress verificaMac;

	public void importarXml() throws NonUniqueResultException {

		Document doc = null;

		SAXBuilder builder = new SAXBuilder();

		try {

			// "\\\\dsamfs\\dados\\publico\\SystemLogs\\Inventario\\temp";
			// //local direto no servidor
			 //String dir = "c:\\xml\\"; // local mapeado no servidor P_JBOSS_VM
			String dir = "d:/temp/"; // local
			File diretorio = new File(dir);

			for (String arquivos : diretorio.list()) {

				if (arquivos.endsWith(".xml")) {
					String arquivo = diretorio.getCanonicalPath() + "\\"
							+ arquivos;
					System.out.println(arquivo);

					try {
						doc = builder.build(dir + arquivos);
					} catch (org.jdom2.input.JDOMParseException e) {
						System.out
								.println("Estrutura do Arquivo .xml é inválida! :(");

					}

					try {
						Element elemento = doc.getRootElement();
						List<Element> lista = elemento.getChildren();

						for (Element e : lista) {

							String host = e.getChildText("HOST").trim();
							String ip = e.getChildText("IP").trim();
							String mac = e.getChildText("MAC").trim();
							String chave_windows = e.getChildText(
									"chave_windows").trim();
							String memoria = e.getChildText("RAM").trim();
							String processador = e.getChildText("CPU").trim();
							String so = e.getChildText("SO").trim();

							System.out.println("HOST: "
									+ e.getChildText("HOST"));

							System.out.println("IP: " + e.getChildText("IP"));
							System.out.println("MAC: " + e.getChildText("MAC"));
							System.out.println("chave_windows: "
									+ e.getChildText("chave_windows"));
							System.out.println("Memória: "
									+ e.getChildText("RAM"));
							System.out.println("Processador: "
									+ e.getChildText("CPU"));
							System.out.println("SO: " + e.getChildText("SO"));

							verificaSO.verificaSistemaOperacional(so);
							this.so = soBean.buscaPorNome(so);

							ChaveSerial chaveSerial = new ChaveSerial();
							chaveSerial.setChaveSerial(chave_windows);

							verificaProcessador
									.verificaProcessador(processador);
							this.processador = processadorDao.buscaPorNome(
									verificaProcessador.getNomeMarca(),
									verificaProcessador.getNomeModelo());

							verificaMemoria.converteMemoria(memoria);
							System.out
									.println("entrando na busca da memoria de LerSax");
							this.memoria = memoriaDao.buscaPorNome("nulo",
									verificaMemoria.getTamanho());

							importaPc.setNome(host);
							importaPc.setIp(ip);

							verificaMac.setMac(mac);
							importaPc.setMacAdress(verificaMac.getMac());

							importaPcBean.setProcessadorId(this.processador
									.getId());
							importaPcBean.setMemoriaId(this.memoria.getId());
							importaPcBean.setSistemaOperacionalId(this.so
									.getId());

							importaPcBean.setImportaPc(importaPc);
							importaPcBean.setChaveSerial(chaveSerial);

							importaPcBean.grava();
						}
					} catch (java.lang.NullPointerException e) {
						System.out
								.println("Estrutura do .xml inválida e/ou tags incorretas");
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
