package mb.dsam.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mb.dsam.modelo.Usuario;

public class UsuarioDAO implements Serializable{

	@PersistenceContext
	EntityManager em;
	
	public boolean existe(Usuario usuario) {
		
		
		
		Query query = em.createQuery("from Usuario where login = :login and senha = :senha")
						.setParameter("login", usuario.getLogin())
						.setParameter("senha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();
		
		return encontrado;
	}
}