package com.servicios;

import com.entities.Rol;
import com.exception.ServiciosException;

//@Remote
public interface RolBeanRemote {

	public void crearRol(Rol rol) throws ServiciosException;
	public Rol buscarRol(long id) throws ServiciosException;
}