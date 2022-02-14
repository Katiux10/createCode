package com.srv;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOS.DAOUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class LoginServicio {
	
	@EJB
	private DAOUsuario daoUsuario;
	private static Usuario usLog;

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUSuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	
	public Usuario login(String nombreUs, String contrasena) throws ServiciosException {
		usLog = daoUsuario.buscarUsuarioLog(nombreUs, contrasena);
	
		return usLog;
	}

}