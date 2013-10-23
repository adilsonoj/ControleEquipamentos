package mb.dsam.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"chaveSerial", "sistemaoperacional_id"})})
public class ChaveSerial implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	private String ChaveSerial;
	
	@OneToOne
	private Pc pc;
	
	@ManyToOne
	private SistemaOperacional sistemaOperacional;

	
	public String getChaveSerial() {
		return ChaveSerial;
	}

	public void setChaveSerial(String chaveSerial) {
		this.ChaveSerial = chaveSerial;
	}

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

	public String getSerial() {
		return ChaveSerial;
	}

	public void setSerial(String serial) {
		this.ChaveSerial = serial;
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
