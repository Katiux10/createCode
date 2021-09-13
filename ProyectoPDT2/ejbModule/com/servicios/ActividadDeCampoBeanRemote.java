package com.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import com.entities.ActividadDeCampo;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Remote
public interface ActividadDeCampoBeanRemote {
	 public void crearActDeCampo(ActividadDeCampo  actividadDeCampo ) throws ServiciosException;
	 public void modificarUsuario(ActividadDeCampo  actividadDeCampo) throws ServiciosException;
	 public ArrayList<ActividadDeCampo> listActForm(long formulario) throws ServiciosException;
	 public ArrayList<ActividadDeCampo> listActFormUsuario(long formulario, Usuario usuario) throws ServiciosException;
	 public List<ActividadDeCampo> listActividad() throws ServiciosException;

}

