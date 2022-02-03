package com.DAOS;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAOUsuario {
	
	@PersistenceContext
	private EntityManager em;
	
	public void crearUsuario(Usuario usuario) throws ServiciosException {
			em.persist(usuario);
			em.flush();
	}

	public void modificarUsuario(Usuario usuario) throws ServiciosException {
		em.merge(usuario);
		em.flush();
	}
	
	public Usuario buscarUsuarioLog(String nombreUsuario, String contrasena) throws ServiciosException {
		String query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasena = ?2";
		Query consulta = em.createQuery(query);
		consulta.setParameter(1, nombreUsuario);
		consulta.setParameter(2, contrasena);
		@SuppressWarnings("unchecked")
		List<Usuario> resultados = consulta.getResultList();
		try {
			return resultados.get(0);

		}catch(Exception e){
			throw new ServiciosException("Usuario o contraseña incorrecta") ;
		}

	}
	
	public Usuario buscarUsuario(long idUsuario) throws ServiciosException {
		Usuario buscarUsuario = em.find(Usuario.class, idUsuario);
		return buscarUsuario;
		
	}
	
	public Usuario buscarNomUs(String nombreUsuario) throws ServiciosException {
		Usuario buscarUs = em.createQuery("SELECT u from Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario.class).
				setParameter("nombreUsuario", nombreUsuario).getSingleResult();

		return buscarUs;
		
	}
	
	public Usuario buscarUsuarioApellido(String Apellido) throws ServiciosException {
		Usuario buscarUsuarioApellido = em.find(Usuario.class, Apellido);
		return buscarUsuarioApellido;
		
	}
	public void darDeBajaUsuario(Usuario usuario) throws ServiciosException {
		Usuario us = em.find(Usuario.class, usuario.getIdUsuario());
		em.remove(us);
		em.flush();
	}
	
	public List<Usuario> obtenerTodosUsuarioss(String nombreUsuario) throws ServiciosException {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class); 
		return query.getResultList();
	}
	
	public List<Usuario> obtenerTodosUsuarios() throws ServiciosException {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class); 
		return query.getResultList();
	}
}