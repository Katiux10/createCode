package com.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.exception.ServiciosException;
import com.srv.UsuarioServicio;


@Named(value="usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<UsuarioDTO> listaDTO;
	
	private UsuarioDTO usuarioDTO;
	
	@EJB
	private UsuarioServicio usuarioServ;
	
	private String contrasena;
	
	
	//Getters y setters
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public UsuarioServicio getUsuarioServ() {
		return usuarioServ;
	}

	public void setUsuarioServ(UsuarioServicio usuarioServ) {
		this.usuarioServ = usuarioServ;
	}

	public List<UsuarioDTO> getListaDTO() {
		return listaDTO;
	}

	public void setListaDTO(List<UsuarioDTO> listaDTO) {
		this.listaDTO = listaDTO;
	}
	
	@PostConstruct
	public void init() {
		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setRol(" ");
		listaDTO = new ArrayList<UsuarioDTO>();
	}
	
	public String crearUsuario() throws ServiciosException {
		
		if(!usuarioDTO.getNombre().isEmpty() && !usuarioDTO.getApellido().isEmpty() && !usuarioDTO.getNombreUsuario().isEmpty() && !usuarioDTO.getEmail().isEmpty() && !usuarioDTO.getContrasena().isEmpty()) {
			usuarioDTO = usuarioServ.crear(usuarioDTO);
			init();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario creado con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
		}
		
		return "altaUsuarios.xhtml";
	
	}
	
	public String modificarUsuario() throws ServiciosException {
		
		if(!usuarioDTO.getNombre().isEmpty() && !usuarioDTO.getApellido().isEmpty() && !usuarioDTO.getNombreUsuario().isEmpty() && !usuarioDTO.getEmail().isEmpty() && !usuarioDTO.getContrasena().isEmpty()) {
			usuarioDTO = usuarioServ.modificar(usuarioDTO);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario modificado con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return "listadoUsuarios.xhtml";
	}
	
	public String buscarUs(UsuarioDTO usDTO) throws ServiciosException{
		String pagina = "";
		usuarioDTO = usuarioServ.buscar(usDTO.getNombreUsuario());
		if(usuarioDTO.getRol().equals("Administrador")) {
			pagina = "modificarAdmin.xhtml";
		}else if(usuarioDTO.getRol().equals("Experto")) {
			pagina = "modificarExperto.xhtml";
		}else {
			pagina = "modificar.xhtml";
		}
		
		return pagina;
	}
	
	public String eliminarUsuario() throws ServiciosException{

		if(usuarioDTO.getIdUsuario() <= 0) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			usuarioServ.eliminar(usuarioDTO);usuarioServ.eliminar(usuarioDTO);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario eliminado con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return "listadoUsuarios.xhtml";
	}
	
	//métodos que se comunican con el DTO
	public void obtenerUsuarios() throws ServiciosException{
		listaDTO = usuarioServ.list();
	}
		
	public boolean muestraFormAdmin() {
		
		if (usuarioDTO.getRol().equals("Administrador")) {
			return true;
		}
		return false;
		
	}

	public boolean muestraFormExperto() {
		if (usuarioDTO.getRol().equals("Experto")) {
			return true;
		}
		return false;
	}

	public boolean muestraFormComun() {
		if (usuarioDTO.getRol().equals("Común")) {
			return true;
		}
		return false;
	}
	
}