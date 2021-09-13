package com.DAOS;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Casilla;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAOCasilla {
	
	@PersistenceContext 
	private EntityManager em;
	
	public void crearCasilla(Casilla casilla) throws ServiciosException {
		em.persist(casilla);
		em.flush();
	}
	
	public Casilla buscarCasillaId(long idCasilla) throws ServiciosException {
		Casilla buscarCasilla= em.find(Casilla.class, idCasilla);
		return buscarCasilla;
	}
	public Casilla buscarCasParametro(String parametro) throws ServiciosException {		
		Casilla casilla = em.createQuery(
				  "SELECT c from Casilla c WHERE c.parametro = :parametro", Casilla.class).
				  setParameter("parametro", parametro).getSingleResult();
		
		return casilla;
		
	}
	
	public List<Casilla> listarCasillas() throws ServiciosException {
		TypedQuery<Casilla> query = em.createQuery("SELECT c FROM Casilla c",Casilla.class); 
		return query.getResultList();
	} 

}
