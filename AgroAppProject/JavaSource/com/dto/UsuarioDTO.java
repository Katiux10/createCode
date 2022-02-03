package com.dto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

public class UsuarioDTO {
	
	private long idUsuario;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String nombreUsuario;
	
	@NotNull
	private String apellido;

	@NotNull
	private String cedula;
	
	@NotNull
	private String contrasena;
	
	private String email;
	
	private String instituto;
	
	private String profesion;
	
	private String rol;
	
	private long idRol;

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getEmail() {
		return email;
	}

	public String getInstituto() {
		return instituto;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public void resetFail() {
		
		this.nombre = null;
		this.nombreUsuario = null;
		this.apellido = null;
		this.cedula = null;
		this.contrasena = null;
		this.email = null;
		this.instituto = null;
		this.profesion = null;
		this.rol = null;

        FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}