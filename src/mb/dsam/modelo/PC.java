package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Pc implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @Column(unique=true)
	private Long numeroPatrimonial;
	private String nome;
	private Integer ip;
	private String macAdress;
	
	private String processador;
	private Integer lacre;
	private String elementoOrganizacional;
	private Integer andar;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true, mappedBy="pc")
	@PrimaryKeyJoinColumn
	private ChaveSerial chaveSerial;
	
	@ManyToOne(optional = true)
	private Memoria memoria;
	
	
		
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Long getNumeroPatrimonial() {
		return numeroPatrimonial;
	}
	public void setNumeroPatrimonial(Long numeroPatrimonial) {
		this.numeroPatrimonial = numeroPatrimonial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public String getMacAdress() {
		return macAdress;
	}
	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}
	public Memoria getMemoria() {
		return memoria;
	}
	public void setMemoria(Memoria memoria) {
		this.memoria = memoria;
	}
	public String getProcessador() {
		return processador;
	}
	public void setProcessador(String processador) {
		this.processador = processador;
	}
	public Integer getLacre() {
		return lacre;
	}
	public void setLacre(Integer lacre) {
		this.lacre = lacre;
	}
	public String getElementoOrganizacional() {
		return elementoOrganizacional;
	}
	public void setElementoOrganizacional(String elementoOrganizacional) {
		this.elementoOrganizacional = elementoOrganizacional;
	}
	
	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}
	
	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}
	
	
}
