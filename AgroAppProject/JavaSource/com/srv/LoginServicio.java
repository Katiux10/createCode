package com.srv;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.DAOS.DAOUsuario;
import com.entities.Rol;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class LoginServicio {
	
	@EJB
	private DAOUsuario daoUsuario;

	public DAOUsuario getDaoUSuario() {
		return daoUsuario;
	}

	public void setDaoUSuario(DAOUsuario daoUSuario) {
		this.daoUsuario = daoUSuario;
	}
	
	public Usuario login(String nombreUs, String contrasena) throws ServiciosException {
		Usuario us = daoUsuario.buscarUsuarioLog(nombreUs, contrasena);
		
		return us;
		
	}

}
