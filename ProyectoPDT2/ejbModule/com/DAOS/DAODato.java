package com.DAOS;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.entities.Dato;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAODato {
	
	@PersistenceContext 
	private EntityManager em;
	
	public void crearDato(Dato dato) throws ServiciosException {
		em.persist(dato);
		em.flush();
	}
	
	public List<Dato> buscarDatoId(long actividadesDeCampo) throws ServiciosException {
		List<Dato> dato = em.createQuery(
				  "SELECT d from ActividadDeCampo d WHERE d.actividadesDeCampo = :actividadesDeCampo", Dato.class).
				  setParameter("actividadesDeCampo", actividadesDeCampo).getResultList();
		
		return dato;
		
	}
}

