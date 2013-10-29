package mb.dsam.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Processador implements Serializable {
	@Id @GeneratedValue
	private Long id;
	private String marca;
	private String modelo;
	
	@OneToMany(mappedBy="processador")
	private List<Pc> pcs;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public List<Pc> getPcs() {
		return pcs;
	}

}
