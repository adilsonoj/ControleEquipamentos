package mb.dsam.mb;



import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mb.dsam.dao.UsuarioDAO;
import mb.dsam.modelo.Usuario;

@RequestScoped
@Named
public class LoginBean implements Serializable{
	
	@Inject
	private Usuario usuario;
	
	
	@Inject
	private UsuarioDAO dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public String efetuarLogin() {
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			usuarioLogado.setUsuario(usuario);
			return "index?faces-redirect=true";
		} else {
			usuarioLogado.setUsuario(null);
			return "login";
		}
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public boolean isLogado(){
		return usuarioLogado.isLogado();
	}
	
	public String logout(){
		usuarioLogado.setUsuario(null);
		return "login?faces-redirect=true";
	}
	
	
	

	
}
