package mb.dsam.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;

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
	PcBean pcBean;
	@Inject
	Pc pc;
	@Inject
	SistemaOperacionalDao soDao;
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
	Processador processadorAux;
	
	private String nomeProcessador;
	private String nomeMarca;
	private String nomeModelo;

	public PcBean getBean() {
		return pcBean;
	}

	public void setBean(PcBean bean) {
		this.pcBean = bean;
	}

	public Pc getPco() {
		return pc;
	}

	public void setPco(Pc pco) {
		this.pc = pco;
	}

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


						
						this.so = soDao.buscaPorNome(e.getChildText("so"));
												
						ChaveSerial cs = new ChaveSerial();
						cs.setChaveSerial(e.getChildText("chaveSerial"));
						
						//verifica processador
						nomeProcessador = e.getChildText("processador");
						if (nomeProcessador.contains("Intel")) {
							nomeMarca = "Intel";
							processadorAux.setMarca(nomeMarca);
							nomeModelo = nomeProcessador.replace("Intel", "");
							processadorAux.setModelo(nomeModelo);
				
						} else if(nomeProcessador.contains("AMD")){
							nomeMarca = "AMD";
							processadorAux.setMarca(nomeMarca);
							nomeModelo = nomeProcessador.replace("AMD", "");
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
						
						this.processador = processadorDao.buscaPorNome(nomeMarca, nomeModelo);
						

						
						Long idMe = new Long(2);
						this.memoria = memoriaDao.busca(idMe);

						Long np = new Long(e.getChildText("numeroPatrimonial"));

						pc.setNome(e.getChildText("nome"));
						pc.setNumeroPatrimonial(np);
						pc.setIp(e.getChildText("ip"));
						pc.setMacAdress(e.getChildText("macAdress"));
						pc.setChaveSerial(cs);
						//pc.setProcessador(this.processador);
						pcBean.setProcessadorId(processador.getId());
						pcBean.setMemoriaId(memoria.getId());
						pcBean.setSistemaOperacionalId(so.getId());
						pcBean.setPc(pc);
						pcBean.setChaveSerial(cs);

						pcBean.grava();

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
