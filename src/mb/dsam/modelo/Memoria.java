package mb.dsam.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Memoria {
	@Id @GeneratedValue
	private Long id;
	private String modelo;
	private String tamanho;
	
	@OneToMany(mappedBy="memoria")
	private List<Pc> pcs;
	@OneToMany(mappedBy="memoria")
	private List<Notebook> notebooks;
	

	public List<Notebook> getNotebooks() {
		return notebooks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public List<Pc> getPcs() {
		return pcs;
	}

	public void setPc(List<Pc> pcs) {
		this.pcs = pcs;
	}
	
	
}
