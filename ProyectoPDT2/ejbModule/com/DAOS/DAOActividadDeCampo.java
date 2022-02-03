package com.DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.ActividadDeCampo;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class DAOActividadDeCampo {

	@PersistenceContext 
	private EntityManager em;
	
	
	public void crearActDeCampo(ActividadDeCampo  actividadDeCampo) throws ServiciosException {
		em.persist(actividadDeCampo);
		em.flush();
	}
	
	public void modificarActDeCampo(ActividadDeCampo  actividadDeCampo) throws ServiciosException {
		em.merge(actividadDeCampo);
		em.flush();
	}
	
	public ArrayList<ActividadDeCampo> listActForm(long formulario) throws ServiciosException {
		TypedQuery <ActividadDeCampo> query = em.createQuery("SELECT DISTINCT a FROM ActividadDeCampo a JOIN FETCH a.datos WHERE a.formulario = :formulario", ActividadDeCampo.class).setParameter("formulario", formulario);
		return (ArrayList<ActividadDeCampo>) query.getResultList();
	}
	
	public ArrayList<ActividadDeCampo> listActFormUsuario(long formulario, Usuario usuario) throws ServiciosException {
		TypedQuery <ActividadDeCampo> query = em.createQuery("SELECT DISTINCT a FROM ActividadDeCampo a JOIN FETCH a.datos WHERE a.formulario = :formulario AND a.usuario = :usuario", ActividadDeCampo.class).setParameter("formulario", formulario).setParameter("usuario", usuario);
		return (ArrayList<ActividadDeCampo>) query.getResultList();
	}
	
	public List<ActividadDeCampo> listActividad() throws ServiciosException {		
		TypedQuery <ActividadDeCampo> query = em.createQuery("SELECT DISTINCT a FROM ActividadDeCampo a JOIN FETCH a.datos", ActividadDeCampo.class);
		return (ArrayList<ActividadDeCampo>) query.getResultList();
		
	}
}
