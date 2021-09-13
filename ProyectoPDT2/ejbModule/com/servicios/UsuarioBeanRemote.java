package com.servicios;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Usuario;
import com.exception.ServiciosException;

//@Remote
public interface UsuarioBeanRemote {
	public void crearUsuario(Usuario usuario) throws ServiciosException;
	public void modificarUsuario(Usuario usuario) throws ServiciosException;
	public Usuario buscarUsuario(long idUsuario) throws ServiciosException;
	public Usuario buscarNomUs(String nombreUsuario) throws ServiciosException;
	public void darDeBajaUsuario(Usuario usuario) throws ServiciosException;
	public List<Usuario> buscarUsuarioLogin(String nombreUsuario, String contrasena) throws ServiciosException;
	public List<Usuario> obtenerTodosUsuarios()throws ServiciosException;
	public Usuario buscarUsuarioNombre(String nombre) throws ServiciosException;
	public List<Usuario> seleccionarUs(String criterioNombre,String criterioRol) throws ServiciosException;
	public Usuario buscarUsuarioLog(String nombreUsuario, String contrasena) throws ServiciosException;
}