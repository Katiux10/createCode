package com.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOS.DAOUsuario;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	private DAOUsuario dAOUsuario;
	
    public UsuarioBean() {
    	
    }

	@Override
	public void crearUsuario(Usuario usuario) throws ServiciosException {
		dAOUsuario.crearUsuario(usuario);
		
	}

	@Override
	public void modificarUsuario(Usuario usuario) throws ServiciosException {
		dAOUsuario.modificarUsuario(usuario);
	}

	@Override
	public Usuario buscarUsuario(long idUsuario) throws ServiciosException {
		return dAOUsuario.buscarUsuario(idUsuario);
		
	}
	
	@Override
	public void darDeBajaUsuario(Usuario usuario) throws ServiciosException {
		dAOUsuario.darDeBajaUsuario(usuario);
	}
	
	@Override
	public List<Usuario> obtenerTodosUsuarios()throws ServiciosException{
		return dAOUsuario.obtenerTodosUsuarios();
	}

	@Override
	public Usuario buscarNomUs(String nombreUsuario) throws ServiciosException {
		return dAOUsuario.buscarNomUs(nombreUsuario);
	}
	
	@Override
	public Usuario buscarUsuarioLog(String nombreUsuario, String contrasena) throws ServiciosException {
		return dAOUsuario.buscarUsuarioLog(nombreUsuario, contrasena);
	}
}