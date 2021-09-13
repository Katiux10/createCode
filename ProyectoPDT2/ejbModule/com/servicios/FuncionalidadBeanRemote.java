package com.servicios;

import javax.ejb.Remote;
import com.entities.Funcionalidad;
import com.exception.ServiciosException;

@Remote
public interface FuncionalidadBeanRemote {
	public void crearFuncionalidad(Funcionalidad funcionalidad) throws ServiciosException;
	public Funcionalidad buscarFunc(long id) throws ServiciosException;
}
