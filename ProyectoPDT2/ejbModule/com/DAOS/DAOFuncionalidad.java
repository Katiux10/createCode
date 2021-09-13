package com.DAOS;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Funcionalidad;
import com.exception.ServiciosException;


@Stateless
@LocalBean
public class DAOFuncionalidad {
	
	@PersistenceContext 
	private EntityManager em;
	

	public void crearFuncionalidad(Funcionalidad funcionalidad) throws ServiciosException {
		em.persist(funcionalidad);
		em.flush();
	}


	public List<Funcionalidad> obtenerTodos() {
		TypedQuery<Funcionalidad> query = em.createQuery("SELECT f FROM Funcionalidad f",Funcionalidad.class); 
		return query.getResultList();
	}


	public List<Funcionalidad> obtenerTodos(String filtro) {
		TypedQuery<Funcionalidad> query = em.createQuery("SELECT DISTINCT f.nombre FROM Funcionalidad f INNER JOIN FETCH f.rol r",Funcionalidad.class)
		.setParameter("nombre", filtro); 
		return query.getResultList();
	}


	public Funcionalidad buscarFunc(long id) throws ServiciosException {
		Funcionalidad buscarFunc = em.find(Funcionalidad.class, id);
		return buscarFunc;
		
	}

}
