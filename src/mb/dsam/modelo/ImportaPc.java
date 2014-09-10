package mb.dsam.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ImportaPc implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long numeroPatrimonial;
	private String nome;
	private String ip;
	private String macAdress;
	private Integer lacre;
	private Integer andar;
	

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true, mappedBy = "importaPc")
	private ChaveSerial chaveSerial;

	@ManyToOne(optional = true)
	private Memoria memoria;
	@ManyToOne(optional = true)
	private Processador processador;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<Software> softwares = new ArrayList<Software>();

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
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

	public Processador getProcessador() {
		return processador;
	}

	public void setProcessador(Processador processador) {
		this.processador = processador;
	}

	public Integer getLacre() {
		return lacre;
	}

	public void setLacre(Integer lacre) {
		this.lacre = lacre;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	

}
