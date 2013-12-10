package mb.dsam.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import mb.dsam.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable {
	
	private Usuario usuario;
	
	public boolean isLogado(){
		return usuario != null;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	
	
	
}
