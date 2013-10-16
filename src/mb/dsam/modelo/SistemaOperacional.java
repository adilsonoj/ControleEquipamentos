package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SistemaOperacional implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@OneToOne(mappedBy = "sistemaOperacional")
	private Pc pc;
	
	
	
	public Pc getPc() {
		return pc;
	}
	public void setPc(Pc pc) {
		this.pc = pc;
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
	
}
