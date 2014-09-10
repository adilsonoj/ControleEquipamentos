package mb.dsam.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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

@ManagedBean(name = "lerSaxBean")
@ViewScoped
public class LerSaxBean implements Serializable {
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
	
	
	public void importarXml() {

		Document doc = null;

		SAXBuilder builder = new SAXBuilder();
		try {

			String dir = "d:/temp/";

			File diretorio = new File(dir);
			for (String arquivos : diretorio.list()) {

				if (arquivos.endsWith(".xml")) {
					String arquivo = diretorio.getCanonicalPath() + "\\"
							+ arquivos;
					System.out.println(arquivo);

					doc = builder.build(dir + arquivos);
					Element elemento = doc.getRootElement();

					List<Element> lista = elemento.getChildren();
					for (Element e : lista) {
						
						System.out.println("pc: " + e.getAttributeValue("id"));
						System.out.println("Nome: " + e.getChildText("nome"));
						System.out.println("numeroPatrimonial: "
								+ e.getChildText("numeroPatrimonial"));
						System.out.println("ip: " + e.getChildText("ip"));
						System.out.println("macAdress: "
								+ e.getChildText("macAdress"));
						System.out.println("chaveSerial: "
								+ e.getChildText("chaveSerial"));
						System.out.println("Memória: "
								+ e.getChildText("memoria"));
						System.out.println("Processador: "
								+ e.getChildText("processador"));
						System.out.println("SO: "
								+ e.getChildText("so"));

						
						verificaSO.verificaSistemaOperacional(e.getChildText("so"));
						this.so = soBean.buscaPorNome(e.getChildText("so"));
												
						ChaveSerial chaveSerial = new ChaveSerial();
						chaveSerial.setChaveSerial(e.getChildText("chaveSerial"));
				
						verificaProcessador.verificaProcessador(e.getChildText("processador"));//verifica processador
						this.processador = processadorDao.buscaPorNome(verificaProcessador.getNomeMarca(),verificaProcessador.getNomeModelo());
													
						verificaMemoria.converteMemoria(e.getChildText("memoria"));
						this.memoria = memoriaDao.buscaPorNome(verificaMemoria.getTamanho());
					
						//Long np = new Long(e.getChildText("numeroPatrimonial"));
						//importaPc.setNumeroPatrimonial(np);
						
						importaPc.setNome(e.getChildText("nome"));
						
						importaPc.setIp(e.getChildText("ip"));
						
						verificaMac.setMac(e.getChildText("macAdress"));
						importaPc.setMacAdress(verificaMac.getMac());
						
						importaPcBean.setProcessadorId(processador.getId());
						importaPcBean.setMemoriaId(memoria.getId());
						importaPcBean.setSistemaOperacionalId(so.getId());
						
						importaPcBean.setImportaPc(importaPc);
						importaPcBean.setChaveSerial(chaveSerial);
				
							importaPcBean.grava();
							
						

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
