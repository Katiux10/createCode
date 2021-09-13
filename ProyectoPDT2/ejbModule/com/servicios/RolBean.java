package com.servicios;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOS.DAORol;
import com.entities.Rol;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class RolBean
 */
@Stateless
@LocalBean
public class RolBean implements RolBeanRemote {

    /**
     * Default constructor. 
     * 
     */
	@EJB
	private DAORol dAORol;
	
    public RolBean() {
    
    }

	@Override
	public void crearRol(Rol rol) throws ServiciosException {
		dAORol.crearRol(rol);
	}
	@Override
	public Rol buscarRol(long id) throws ServiciosException{
		return dAORol.buscarRol(id);
	}
}