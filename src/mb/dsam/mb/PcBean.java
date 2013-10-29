package mb.dsam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mb.dsam.dao.ChaveSerialDao;
import mb.dsam.dao.MemoriaDao;
import mb.dsam.dao.PcDao;
import mb.dsam.dao.ProcessadorDao;
import mb.dsam.dao.SistemaOperacionalDao;
import mb.dsam.modelo.ChaveSerial;
import mb.dsam.modelo.Memoria;
import mb.dsam.modelo.Pc;
import mb.dsam.modelo.Processador;
import mb.dsam.modelo.SistemaOperacional;

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
	
	private Long sistemaOperacionalId;
	private String serial;
	private Long memoriaId;
	private List<ChaveSerial> chavesSeriais;
	
	private List<Pc> pcs;
	

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}
	
	public void grava() {
			Processador processadorRelacionado = processadorDao.busca(this.processadorId);
			this.pc.setProcessador(processadorRelacionado);
			
			Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
			this.pc.setMemoria(memoriaRelacionado);
			pcDao.adiciona(pc);
			
			Pc pcRelacionado = pcDao.busca(pc.getNumeroPatrimonial());
			this.chaveSerial.setPc(pcRelacionado);
			
			SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
			this.chaveSerial.setSistemaOperacional(soRelacionado);
			
			
			this.chaveSerialDao.adiciona(chaveSerial);
			
			this.chavesSeriais = chaveSerialDao.lista();
			this.pcs = pcDao.listaComChave();
			
			limpaFormularioDoJSF();
		
	}
	
	public void altera(){
		Processador processadorRelacionado = processadorDao.busca(this.processadorId);
		this.pc.setProcessador(processadorRelacionado);
		
		Memoria memoriaRelacionado = memoriaDao.busca(this.memoriaId);
		this.pc.setMemoria(memoriaRelacionado);
		pcDao.altera(pc);
		
		ChaveSerial chave = chaveSerialDao.buscaPorPc(pc);
		
		
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		chave.setSistemaOperacional(soRelacionado);
		chave.setChaveSerial(this.chaveSerial.getSerial());
		this.chaveSerialDao.altera(chave);
		
		this.chavesSeriais = chaveSerialDao.lista();
		this.pcs = pcDao.listaComChave();
		limpaFormularioDoJSF();
	
	}
	
	private void gravaChaveSerial(){
		SistemaOperacional soRelacionado = soDao.busca(this.sistemaOperacionalId);
		this.chaveSerial.setSistemaOperacional(soRelacionado);
		this.chaveSerialDao.adiciona(chaveSerial);
		
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
	
	public List<Pc> getPcsComChaveSerial(){
		return pcDao.listaComChave();
	}

	public void remove(Pc pc) {
		System.out.println("Removendo o pc");
		pcDao.remove(pc);
		this.pcs=pcDao.lista();
		limpaFormularioDoJSF();
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
		return chaveSerial;
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

}
