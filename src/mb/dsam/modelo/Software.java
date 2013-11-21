package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Software implements Serializable {
	@Id @GeneratedValue
	private Long id;
	private String nome;
	@Column(name="padrao", nullable=false) 
	private Boolean padrao;
	
	
	
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
