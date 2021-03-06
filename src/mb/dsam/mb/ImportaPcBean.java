package mb.dsam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.ImportaPcDao;
import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.PcDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.dao.SoftwareDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.ImportaPc;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.modelo.Software;

@ViewScoped
@ManagedBean
public class ImportaPcBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ImportaPc importaPc;
	@Inject
	private Pc pc;
	@Inject
	private PcDao pcDao;
	@Inject
	private ImportaPcDao importaPcDao;
	@Inject
	private ChaveSerial chaveSerial;
	@Inject
	private ChaveSerialDao chaveSerialDao;
	@Inject
	private SistemaOperacionalDao soDao;
	@Inject
	private Memoria memoria;
	@Inject
	private MemoriaDao memoriaDao;
	@Inject
	private Processador processador;
	@Inject
	private ProcessadorDao processadorDao;
	
	private Long processadorId;
	private Long numeroPatrimonial;
	private Long sistemaOperacionalId;
	private String serial;
	private String ip;
	private String nome;
	private Long memoriaId;
	private String observacao;
	private List<ChaveSerial> chavesSeriais;
	private List<ImportaPc> pcs;
	private List<Pc> Pcs;
	
	public ImportaPc getImportaPc() {
		return importaPc;
	}

	public void setImportaPc(ImportaPc importaPc) {
		this.importaPc = importaPc;
	}

	public void grava() {
		
		try {
			
			System.out.println("J� existe esse PC na base de produ��o" + this.pcDao.buscaPorMac(this.importaPc.getMacAdress()));
		} catch (Exception e) {
			Processador processadorRelacionado = processadorDao
					.busca(this.processadorId);
			this.importaPc.setProcessador(processadorRelacionado);

			Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
			this.importaPc.setMemoria(memoriaRelacionado);
			try {
				importaPcDao.altera(importaPc);
				
				ImportaPc pcRelacionado = importaPcDao.buscaPorMac(importaPc.getMacAdress());
				this.chaveSerial.setImportaPc(pcRelacionado);
				
				SistemaOperacional soRelacionado = soDao
						.busca(this.sistemaOperacionalId);
				this.chaveSerial.setSistemaOperacional(soRelacionado);
			} catch (Exception e1) {
				System.out.println("ImportaPcBean: NP j� existe");
			}
			
			
			try {
				this.chaveSerialDao.altera(chaveSerial);
				
				this.chavesSeriais = chaveSerialDao.lista();
				this.pcs = importaPcDao.listaComChave();
			} catch (Exception e2) {
				System.out.println("ImportaPcBean: Chave Serial j� existe");
			}
			
			
			limpaFormularioDoJSF();
			
		}
			
			
		
	}
	
	public void importaParaPc() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			if (this.importaPcDao.busca(this.importaPc.getNumeroPatrimonial()).getNumeroPatrimonial() == this.importaPc.getNumeroPatrimonial()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira o N�mero Patrimonial!", null));
			}
			
		} catch (Exception e) {
			
			this.pc.setNumeroPatrimonial(this.importaPc.getNumeroPatrimonial());
			this.pc.setIp(this.importaPc.getIp());
			this.pc.setNome(this.importaPc.getNome());
			this.pc.setMacAdress(this.importaPc.getMacAdress());
			this.pc.setAndar(this.importaPc.getAndar());
			this.pc.setLacre(this.importaPc.getLacre());
			this.pc.setObservacao(getObservacao());
			Processador processadorRelacionado = processadorDao
					.busca(this.processadorId);
			this.pc.setProcessador(processadorRelacionado);

			Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
			this.pc.setMemoria(memoriaRelacionado);

			pcDao.altera(pc);

			Pc pcRelacionado = pcDao.busca(pc.getNumeroPatrimonial());
			this.chaveSerial.setPc(pcRelacionado);


			SistemaOperacional soRelacionado = soDao
					.busca(this.sistemaOperacionalId);
			this.chaveSerial.setSistemaOperacional(soRelacionado);
			this.chaveSerial.setImportaPc(null);
			this.chaveSerialDao.altera(chaveSerial);
			
			removePorMac(importaPc);
			
			this.chavesSeriais = chaveSerialDao.lista();
			this.Pcs = pcDao.listaComChave();

			limpaFormularioDoJSF();
		} 
		
		
	}


	

	public void altera() {

		Processador processadorRelacionado = processadorDao
				.busca(this.processadorId);
		this.importaPc.setProcessador(processadorRelacionado);

		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.importaPc.setMemoria(memoriaRelacionado);
		importaPcDao.altera(this.importaPc);

		ChaveSerial chave = chaveSerialDao.buscaPorPc(this.importaPc
				.getNumeroPatrimonial());

		SistemaOperacional soRelacionado = soDao
				.busca(this.sistemaOperacionalId);
		chave.setSistemaOperacional(soRelacionado);
		chave.setChaveSerial(this.chaveSerial.getSerial());

		this.chaveSerialDao.altera(chave);

		this.chavesSeriais = chaveSerialDao.lista();
		this.pcs = importaPcDao.listaComChave();
		limpaFormularioDoJSF();

	}


	public Long getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Long sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	public List<ImportaPc> getPcs() {
		System.out.println("Listando os pcs");
		if (this.pcs == null) {
			this.pcs = importaPcDao.lista();
		}
		return this.pcs;
	}

	public List<ImportaPc> getPcsComChaveSerial() {
		return importaPcDao.listaComChave();
	}

	public List<ImportaPc> getListIps() {
		System.out.println(importaPcDao.listaIps());
		return importaPcDao.listaIps();
	}

	public void remove(ImportaPc pc) {
		System.out.println("Removendo o pc");
		importaPcDao.remove(pc);
		this.pcs = importaPcDao.lista();

		limpaFormularioDoJSF();

	}
	
	public void removePorMac(ImportaPc pc) {
		System.out.println("Removendo o pc");
		importaPcDao.removePorMac(pc.getMacAdress());
		this.pcs = importaPcDao.lista();

		limpaFormularioDoJSF();

	}

	public ImportaPc getPcPorNp() {
		return importaPcDao.buscaPorNp(this.numeroPatrimonial);
	}

	public List<ImportaPc> getPcPorIp() {
		return importaPcDao.buscaPorIp(this.ip);
	}

	public List<ImportaPc> getPcPorNome() {
		return importaPcDao.buscaPorNome(this.nome);
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.importaPc = new ImportaPc();
		this.chaveSerial = new ChaveSerial();
		setObservacao(null);
	}

	public ChaveSerial getChaveSerial() {
		if (importaPc.getNumeroPatrimonial() != null){
			try {
				chaveSerial  = chaveSerialDao.buscaPorImportaPc(importaPc.getNumeroPatrimonial());
			} catch (Exception e) {
				
			}
			return chaveSerial;
				
	}else {
		return chaveSerial;
	}
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}

	public List<ChaveSerial> getChavesSeriais() {
		return chavesSeriais;
	}

	public void setChavesSeriais(List<ChaveSerial> chavesSeriais) {
		this.chavesSeriais = chavesSeriais;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Long getMemoriaId() {
		return memoriaId;
	}

	public void setMemoriaId(Long memoriaId) {
		this.memoriaId = memoriaId;
	}

	public Memoria getMemoria() {
		return memoria;
	}

	public void setMemoria(Memoria memoria) {
		this.memoria = memoria;
	}

	public Processador getProcessador() {
		return processador;
	}

	public void setProcessador(Processador processador) {
		this.processador = processador;
	}

	public Long getProcessadorId() {
		return processadorId;
	}

	public void setProcessadorId(Long processadorId) {
		this.processadorId = processadorId;
	}

	public Long getNumeroPatrimonial() {
		return numeroPatrimonial;
	}

	public void setNumeroPatrimonial(Long numeroPatrimonial) {
		this.numeroPatrimonial = numeroPatrimonial;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	

}
