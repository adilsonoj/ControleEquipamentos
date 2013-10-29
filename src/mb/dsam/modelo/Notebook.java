package mb.dsam.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Notebook implements Serializable{

	@Id
	private Integer numeroPatrimonial;
	private String nome;
	private Long ip;
	private String macAdress;
	
	
	
	@OneToMany(mappedBy="notebook")
	private List<Emprestimo> emprestimos;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true, mappedBy="notebook")
	@PrimaryKeyJoinColumn
	private ChaveSerial chaveSerial;
	
	@ManyToOne(optional = true)
	private Memoria memoria;
	@ManyToOne(optional = true)
	private Processador processador;
		
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
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
	public Long getIp() {
		return ip;
	}
	public void setIp(Long ip) {
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

	public ChaveSerial getChaveSerial() {
		return chaveSerial;
	}

	public void setChaveSerial(ChaveSerial chaveSerial) {
		this.chaveSerial = chaveSerial;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	
	
}
