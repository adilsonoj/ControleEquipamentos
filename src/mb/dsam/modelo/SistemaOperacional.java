package mb.dsam.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SistemaOperacional implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	
	@OneToMany(mappedBy="sistemaOperacional")
	private List<ChaveSerial> seriais;
	
	
	public List<ChaveSerial> getSeriais() {
		return seriais;
	}
	public void setSeriais(List<ChaveSerial> seriais) {
		this.seriais = seriais;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
