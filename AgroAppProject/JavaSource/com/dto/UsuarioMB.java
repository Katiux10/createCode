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

	
	//Getters y setters

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
	
	//m�todos que se comunican con el DTO
	public void obtenerUsuarios() throws ServiciosException{
		listaDTO = usuarioServ.list();
	}
		
	public String crearUsuario() throws ServiciosException {
		usuarioDTO = usuarioServ.crear(usuarioDTO);
		
		if(usuarioDTO.getIdUsuario() <= 0) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario creado con �xito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		
		}
		
		return "gestionUsuario.xhtml";
	
	}
	
	public String modificarUsuario() throws ServiciosException {
		usuarioDTO = usuarioServ.modificar(usuarioDTO);
		
		if(usuarioDTO.getIdUsuario() <= 0) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario modificado con �xito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return "listadoUsuarios.xhtml";
	}
	
	//Se utiliza en listado de usuarios en los botones de editar y eliminar
	public String buscarUs(UsuarioDTO usDTO) throws ServiciosException{
		usuarioDTO = usuarioServ.buscar(usDTO.getNombreUsuario());
		
		return "modificar.xhtml";
	}
	
	public String eliminarUsuario() throws ServiciosException{
		usuarioServ.eliminar(usuarioDTO);
		
		if(usuarioDTO.getIdUsuario() <= 0) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el usuario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario eliminado con �xito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return "listadoUsuarios.xhtml";
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
		if (usuarioDTO.getRol().equals("Com�n")) {
			return true;
		}
		return false;
	}
	
	
}