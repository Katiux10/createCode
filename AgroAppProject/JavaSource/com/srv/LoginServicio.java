package com.srv;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOS.DAOUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;
import org.apache.commons.codec.digest.DigestUtils;

@Stateless
@LocalBean
public class LoginServicio {
	
	@EJB
	private DAOUsuario daoUsuario;
	private static Usuario usLog;
	private String encriptContra;

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUSuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	
	public Usuario login(String nombreUs, String contrasena) throws ServiciosException {
		encriptContra = DigestUtils.sha1Hex(contrasena);
		usLog = daoUsuario.buscarUsuarioLog(nombreUs, encriptContra);
	
		return usLog;
	}

}