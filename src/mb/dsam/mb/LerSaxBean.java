package mb.dsam.mb;

import java.io.File;
import java.io.FileInputStream;
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

						Long ids = new Long(3);

						this.so = soDao.busca(ids);

						System.out.println("So nome: " + so.getNome());

						ChaveSerial cs = new ChaveSerial();
						cs.setChaveSerial(e.getChildText("chaveSerial"));

						Long id = new Long(1);
						this.processador = processadorDao.busca(id);
						System.out.println(this.processador.getMarca());

						Long idMe = new Long(2);
						this.memoria = memoriaDao.busca(idMe);

						Long np = new Long(e.getChildText("numeroPatrimonial"));

						pc.setNome(e.getChildText("nome"));
						pc.setNumeroPatrimonial(np);
						pc.setIp(e.getChildText("ip"));
						pc.setMacAdress(e.getChildText("macAdress"));
						pc.setChaveSerial(cs);
						pc.setProcessador(this.processador);
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
