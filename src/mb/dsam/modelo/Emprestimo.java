package mb.dsam.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private Integer numeroPatrimonial;
	private String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataSaida;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEntrada;
	
	
	@OneToOne
	private Notebook notebook;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumeroPatrimonial() {
		return numeroPatrimonial;
	}
	public void setNumeroPatrimonial(Integer numeroPatrimonial) {
		this.numeroPatrimonial = numeroPatrimonial;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Calendar getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Calendar getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	
}
