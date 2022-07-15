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
import org.apache.commons.codec.digest.DigestUtils;

@Stateless
@LocalBean
public class UsuarioServicio {
	
	@EJB
	private DAOUsuario daoUsuario;
	@EJB
	private DAORol daoRol;
	private String encript;
	
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
		UsuarioDTO usDTO = new UsuarioDTO();
		usDTO.setIdUsuario(usuario.getIdUsuario());
		usDTO.setApellido(usuario.getApellido());
		usDTO.setCedula(usuario.getCedula());
		encript = DigestUtils.sha1Hex(usuario.getContrasena());
		usDTO.setContrasena(encript);
		usDTO.setEmail(usuario.getEmail());
		usDTO.setNombre(usuario.getNombre());
		usDTO.setNombreUsuario(usuario.getNombreUsuario());
		usDTO.setInstituto(usuario.getInstituto());
		usDTO.setProfesion(usuario.getProfesion());
		usDTO.setRol(usuario.getRol().getNombre());
		
		return usDTO;
	}

	public Usuario toUsuario(UsuarioDTO usuarioDTO) throws ServiciosException{
		Usuario us = new Usuario();
		us.setIdUsuario(usuarioDTO.getIdUsuario());
		us.setNombreUsuario(usuarioDTO.getNombreUsuario());
		us.setApellido(usuarioDTO.getApellido());
		us.setCedula(usuarioDTO.getCedula());
		encript = DigestUtils.sha1Hex(usuarioDTO.getContrasena());
		us.setContrasena(encript);
		us.setEmail(usuarioDTO.getEmail());
		us.setInstituto(usuarioDTO.getInstituto());
		us.setNombre(usuarioDTO.getNombre());
		us.setProfesion(usuarioDTO.getProfesion());

		if (usuarioDTO.getRol().equals("Administrador")) {
            us.setRol(daoRol.buscarRol(1L));
		} else if (usuarioDTO.getRol().equals("Experto")){
			us.setRol(daoRol.buscarRol(2L));
		}else {
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