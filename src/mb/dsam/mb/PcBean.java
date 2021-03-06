package mb.dsam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.PcDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.dao.SoftwareDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;
import mb.dsam.modelo.Software;

@ViewScoped
@ManagedBean
public class PcBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pc pc;
	@Inject
	private PcDao pcDao;
	@Inject
	private ChaveSerial chaveSerial;
	
	@Inject
	private ChaveSerialBean chaveSerialBean;
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
	@Inject
	private SoftwareDao softwareDao;
	@Inject
	private Software software;

	private Long processadorId;
	private Long numeroPatrimonial;
	private Long sistemaOperacionalId;
	private String serial;
	private String ip;
	private String nome;
	private Long memoriaId;
	private Long softwareId;
	private List<ChaveSerial> chavesSeriais;
	private List<Software> softwareAux = new ArrayList<Software>();
	private List<Pc> pcs;
	
	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

	public void grava() {

		Processador processadorRelacionado = processadorDao
				.busca(this.processadorId);
		this.pc.setProcessador(processadorRelacionado);

		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.pc.setMemoria(memoriaRelacionado);

		pcDao.altera(pc);
		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("softwares3_.Pc0_numeroPatrimonial")); 
		Pc pcRelacionado = pcDao.busca(pc.getNumeroPatrimonial());
		this.chaveSerial.setPc(pcRelacionado);

		SistemaOperacional soRelacionado = soDao
				.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);

		this.chaveSerialDao.adiciona(chaveSerial);

		this.chavesSeriais = chaveSerialDao.lista();
		this.pcs = pcDao.listaComChave();

		limpaFormularioDoJSF();
	}

	public void gravaXML() {

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

		this.chaveSerialDao.adiciona(chaveSerial);

		this.chavesSeriais = chaveSerialDao.lista();
		this.pcs = pcDao.listaComChave();

		limpaFormularioDoJSF();
	}

	public void altera() {

		Processador processadorRelacionado = processadorDao
				.busca(this.processadorId);
		this.pc.setProcessador(processadorRelacionado);

		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.pc.setMemoria(memoriaRelacionado);
		pcDao.altera(this.pc);

		ChaveSerial chave = chaveSerialDao.buscaPorPc(this.pc
				.getNumeroPatrimonial());

		SistemaOperacional soRelacionado = soDao
				.busca(this.sistemaOperacionalId);
		chave.setSistemaOperacional(soRelacionado);
		chave.setChaveSerial(this.chaveSerial.getSerial());

		this.chaveSerialDao.altera(chave);

		this.chavesSeriais = chaveSerialDao.lista();
		this.pcs = pcDao.listaComChave();
		limpaFormularioDoJSF();

	}

	public void guardaItem() {
		Software soft = softwareDao.busca(softwareId);
		this.pc.getSoftwares().add(soft);

	}

	public void guardaItemAux() {
		Software soft = softwareDao.busca(softwareId);
		this.softwareAux.add(soft);

	}

	public List<Software> getSoftwareAux() {
		return softwareAux;
	}

	public void editaSoftware() {
		pcDao.altera(this.pc);
		software = new Software();
		limpaFormularioDoJSF();
	}

	public void consultaSoftware() {
		this.pc = pcDao.busca(this.numeroPatrimonial);
		this.pc.getSoftwares();
		software = new Software();
	}

	public Long getSistemaOperacionalId() {
		return sistemaOperacionalId;
	}

	public void setSistemaOperacionalId(Long sistemaOperacionalId) {
		this.sistemaOperacionalId = sistemaOperacionalId;
	}

	public List<Pc> getPcs() {
		System.out.println("Listando os pcs");
		if (this.pcs == null) {
			this.pcs = pcDao.lista();
		}
		return this.pcs;
	}

	public List<Pc> getPcsComChaveSerial() {
		return pcDao.listaComChave();
	}

	public List<Pc> getListIps() {
		System.out.println(pcDao.listaIps());
		return pcDao.listaIps();
	}

	public void remove(Pc pc) {
		System.out.println("Removendo o pc");
		pcDao.remove(pc);
		this.pcs = pcDao.lista();

		limpaFormularioDoJSF();

	}

	public List<Pc> getPcPorNp() {
		return pcDao.buscaPorNp(this.numeroPatrimonial);
	}

	public List<Pc> getPcPorIp() {
		return pcDao.buscaPorIp(this.ip);
	}

	public List<Pc> getPcPorNome() {
		return pcDao.buscaPorNome(this.nome);
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.pc = new Pc();
		this.chaveSerial = new ChaveSerial();
	}

	public ChaveSerial getChaveSerial() {
		if (pc.getNumeroPatrimonial() != null){
				try {
					chaveSerial  = chaveSerialDao.buscaPorPc(pc.getNumeroPatrimonial());
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

	public Software getSoftware() {
		return software;
	}

	public Long getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(Long softwareId) {
		this.softwareId = softwareId;
	}


}
