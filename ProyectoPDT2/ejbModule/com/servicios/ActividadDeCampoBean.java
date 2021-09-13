package com.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOS.DAOActividadDeCampo;
import com.entities.ActividadDeCampo;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ActividadDeCampoBean
 */
@Stateless(name = "ActividadDeCampoBean")
public class ActividadDeCampoBean implements ActividadDeCampoBeanRemote {

    /**
     * Default constructor. 
     */
	@EJB
	private DAOActividadDeCampo daoActividadDeCampo;
    public ActividadDeCampoBean() {
        
    }
    
    public void crearActDeCampo(ActividadDeCampo  actividadDeCampo ) throws ServiciosException{
    	daoActividadDeCampo.crearActDeCampo(actividadDeCampo);
    }
 
    public void modificarUsuario(ActividadDeCampo  actividadDeCampo) throws ServiciosException {
    	daoActividadDeCampo.modificarActDeCampo(actividadDeCampo);
    }
   
    public List<ActividadDeCampo> listActividad() throws ServiciosException{
    	return daoActividadDeCampo.listActividad();
    }
    
    public ArrayList<ActividadDeCampo> listActForm(long formulario) throws ServiciosException{
    	return daoActividadDeCampo.listActForm(formulario);
    }
    public ArrayList<ActividadDeCampo> listActFormUsuario(long formulario, Usuario usuario) throws ServiciosException{
    	return daoActividadDeCampo.listActFormUsuario(formulario, usuario);
    }
}
