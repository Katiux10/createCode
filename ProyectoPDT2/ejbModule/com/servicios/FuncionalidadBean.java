package com.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOS.DAOFuncionalidad;
import com.entities.Funcionalidad;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FuncionalidadBean
 */
@Stateless
public class FuncionalidadBean implements FuncionalidadBeanRemote{

    /**
     * Default constructor. 
     */
	
	@EJB
	private DAOFuncionalidad dAOFuncionalidad;
	
    public FuncionalidadBean() {
        
    }

	@Override
	public void crearFuncionalidad(Funcionalidad funcionalidad) throws ServiciosException {
		dAOFuncionalidad.crearFuncionalidad(funcionalidad);
	}

	@Override
	public Funcionalidad buscarFunc(long id) throws ServiciosException {
		return dAOFuncionalidad.buscarFunc(id);
		
	}
}
