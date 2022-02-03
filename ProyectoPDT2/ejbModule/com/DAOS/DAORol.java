package com.DAOS;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Rol;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAORol {
	
	@PersistenceContext 
	private EntityManager em;
	
	public String saludo(String nombre) {
		return nombre;
	}
	
	public void crearRol(Rol rol) throws ServiciosException {
		em.persist(rol);
		em.flush();
	}
	
	public Rol buscarRol(long id) throws ServiciosException{
		Rol buscarol = em.find(Rol.class, id);
		return buscarol;
	}
	public List<Rol> listarRol() throws ServiciosException {
		TypedQuery<Rol> query = em.createQuery("SELECT r FROM Rol r",Rol.class); 
		return query.getResultList();
	} 

}