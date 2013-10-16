package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class SistemaOperacional implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@OneToOne
	private Pc pc;
	
	@OneToOne
	private Notebook notebook;
	private String chave;
	
	@Enumerated(EnumType.STRING)
	private TipoSistemaOperacional tipoSistemaOperacional;
	
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
	
	public void setTipoSistemaOperacional(
			TipoSistemaOperacional tipoSistemaOperacional) {
		this.tipoSistemaOperacional = tipoSistemaOperacional;
	}
	
	public TipoSistemaOperacional getTipoSistemaOperacional() {
		return tipoSistemaOperacional;
	}
	
	public Pc getPc() {
		return pc;
	}
	public void setPc(Pc pc) {
		this.pc = pc;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	
}
