package com.srv;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.DAOS.DAORol;
import com.DAOS.DAOUsuario;
import com.dto.UsuarioDTO;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class UsuarioServicio {
	
	@EJB
	private DAOUsuario daoUsuario;
	
	@EJB
	private DAORol daoRol;

	public DAOUsuario getDaoUSuario() {
		return daoUsuario;
	}

	public void setDaoUSuario(DAOUsuario daoUSuario) {
		this.daoUsuario = daoUSuario;
	}
	
	//devuelve lista usuarios DTO hacia MB
	public List<UsuarioDTO> list() throws ServiciosException {
		List<UsuarioDTO> listUsDTO = new ArrayList<UsuarioDTO>(); 
		List<Usuario> listUs = daoUsuario.obtenerTodosUsuarios();
		for(Usuario u: listUs ) {
			listUsDTO.add(toDTO(u));
		}
		return listUsDTO;
	}
	
	public UsuarioDTO toDTO(Usuario usuario) throws ServiciosException{
		UsuarioDTO us = new UsuarioDTO();
		us.setIdUsuario(usuario.getIdUsuario());
		us.setApellido(usuario.getApellido());
		us.setCedula(usuario.getCedula());
		us.setContrasena(usuario.getContrasena());
		us.setEmail(usuario.getEmail());
		us.setNombre(usuario.getNombre());
		us.setNombreUsuario(usuario.getNombreUsuario());
		us.setInstituto(usuario.getInstituto());
		us.setProfesion(usuario.getProfesion());
		us.setRol(usuario.getRol().getNombre());
		
		return us;
	}

	public Usuario toUsuario(UsuarioDTO usuarioDTO) throws ServiciosException{
		Usuario us = new Usuario();
		us.setIdUsuario(usuarioDTO.getIdUsuario());
		us.setNombreUsuario(usuarioDTO.getNombreUsuario());
		us.setApellido(usuarioDTO.getApellido());
		us.setCedula(usuarioDTO.getCedula());
		us.setContrasena(usuarioDTO.getContrasena());
		us.setEmail(usuarioDTO.getEmail());
		us.setInstituto(usuarioDTO.getInstituto());
		us.setNombre(usuarioDTO.getNombre());
		us.setProfesion(usuarioDTO.getProfesion());

		if (usuarioDTO.getRol().equals("Administrador")) {
            us.setRol(daoRol.buscarRol(1L));
		} else if (usuarioDTO.getRol().equals("Experto")){
			us.setRol(daoRol.buscarRol(2L));
			
		}else if (usuarioDTO.getRol().equals("Común")){
			us.setRol(daoRol.buscarRol(3L));
		}

		return us;
	}
	
	//devuelve usuario DTO hacia MB
	public UsuarioDTO crear(UsuarioDTO usuarioDTO) throws ServiciosException {
		Usuario us = new Usuario();
		us = toUsuario(usuarioDTO);
			
		daoUsuario.crearUsuario(us);
		
		UsuarioDTO usDTO = new UsuarioDTO();//retorna el Usuario convertido a DTO
		usDTO = toDTO(us);
		return usDTO;
	}
	
	public UsuarioDTO modificar(UsuarioDTO usuarioDTO) throws ServiciosException {
		Usuario u1 = new Usuario();
		u1= toUsuario(usuarioDTO);//retorna el DTO convertido en usuario
		
		//modifico ese usuario
		daoUsuario.modificarUsuario(u1);
		
		UsuarioDTO usDTO = new UsuarioDTO();//retorna el Usuario convertido a DTO
		usDTO = toDTO(u1);
		
		return usDTO;
	}
	
	public UsuarioDTO buscar(String nombreUsuario) throws ServiciosException {
		UsuarioDTO uDTO = new UsuarioDTO();
		Usuario u1 = new Usuario();
		u1 = daoUsuario.buscarNomUs(nombreUsuario);
		uDTO = toDTO(u1);

		return uDTO ;	
	}
	
	public void eliminar(UsuarioDTO usuarioDTO) throws ServiciosException {
		Usuario u1 = new Usuario();
		u1= toUsuario(usuarioDTO);//retorna el DTO convertido en usuario
		
		//elimino ese usuario
		daoUsuario.darDeBajaUsuario(u1);
	}
}