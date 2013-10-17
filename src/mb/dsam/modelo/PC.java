package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pc implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer numeroPatrimonial;
	private String nome;
	private Integer ip;
	private String macAdress;
	private String memoria;
	private String processador;
	private Integer lacre;
	private String elementoOrganizacional;
	private Integer andar;
	
	@OneToOne
	private ChaveSerial chaveSerial;
	
		
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Integer getNumeroPatrimonial() {
		return numeroPatrimonial;
	}
	public void setNumeroPatrimonial(Integer numeroPatrimonial) {
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
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
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
	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}
	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}
	
	
	
}
