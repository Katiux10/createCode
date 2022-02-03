package com.DAOS;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Casilla;
import com.entities.Formulario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAOFormulario {
	
	@PersistenceContext 
	private EntityManager em;
	
	public void crearFormulario(Formulario formulario) throws ServiciosException {
		try {
			em.persist(formulario);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarFormulario(Formulario formulario) throws ServiciosException {
		em.merge(formulario);
		em.flush();
	}
	
	public Formulario buscarFormularioId(long idFormulario) throws ServiciosException {
		Formulario buscarForm = em.find(Formulario.class, idFormulario);
		return buscarForm;
	}
	
	public void eliminarCasillaForm(Long idCasilla, Long idFormulario) throws ServiciosException {
		Formulario formulario= em.find(Formulario.class, idFormulario);
		formulario.getCasillas().remove(em.find(Casilla.class, idCasilla));
		em.flush();
	}

	public List<Formulario> listarTodosFormularios() throws ServiciosException {
		TypedQuery<Formulario> query = em.createQuery("SELECT f FROM Formulario f",Formulario.class); 
		return query.getResultList();
	}

	public void asignarCasilla(Long idCasilla, Long idFormulario) throws ServiciosException {
			Formulario formulario= em.find(Formulario.class, idFormulario);
			formulario.getCasillas().add(em.find(Casilla.class, idCasilla));
			em.flush();
	}
	
	public void asignarCasillaOb(Long idCasilla, Long idFormulario) throws ServiciosException {
		Formulario formulario= em.find(Formulario.class, idFormulario);
		formulario.getCasillas().add(em.find(Casilla.class, idCasilla));
		em.flush();
	}
	public Formulario buscarFormularioNom(String nombreFormulario) throws ServiciosException {		
		Formulario formulario = em.createQuery(
				  "SELECT f from Formulario f WHERE f.nombreFormulario = :nombreFormulario", Formulario.class).
				  setParameter("nombreFormulario", nombreFormulario).getSingleResult();
		
		return formulario;
		
	}
	
	public ArrayList<Casilla> listCasForm(Long idFormulario) throws ServiciosException {
		TypedQuery <Casilla> query = em.createQuery("SELECT c FROM Casilla c join c.formularios f WHERE f.idFormulario = :idFormulario order by c.idCasilla", Casilla.class).setParameter("idFormulario", idFormulario);
		return (ArrayList<Casilla>) query.getResultList();
	}
	
}