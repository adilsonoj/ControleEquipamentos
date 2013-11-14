package mb.dsam.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Software implements Serializable {
	@Id @GeneratedValue
	private Long id;
	private String nome;
	@Column(name="padrao", nullable=false) 
	private Boolean padrao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="software")
	private List<SoftwarePc> softwaresPc = new ArrayList<SoftwarePc>();

	
	public List<SoftwarePc> getSoftwaresPc() {
		return softwaresPc;
	}
	public void setSoftwaresPc(List<SoftwarePc> softwaresPc) {
		this.softwaresPc = softwaresPc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getPadrao() {
		return padrao;
	}
	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}

	
	
	
	
}
