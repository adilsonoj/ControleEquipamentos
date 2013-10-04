package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SistemaOperacional implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String chave;
	
	@Enumerated(EnumType.STRING)
	private TipoSistemaOperacional tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public TipoSistemaOperacional getTipo() {
		return tipo;
	}
	public void setTipo(TipoSistemaOperacional tipo) {
		this.tipo = tipo;
	}
	
	
}
