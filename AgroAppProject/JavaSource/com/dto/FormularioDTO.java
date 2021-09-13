package com.dto;

import javax.validation.constraints.NotNull;

import com.entities.Usuario;

public class FormularioDTO {
	
	private long idFormulario;
	
	@NotNull
	private String nombreForm;
	
	private String resumen;
	
	private Usuario usuario;

	public FormularioDTO() {
		super();
	}
	
	public long getIdFormulario() {
		return idFormulario;
	}

	public String getNombreForm() {
		return nombreForm;
	}

	public String getResumen() {
		return resumen;
	}

	public void setIdFormulario(long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public void setNombreForm(String nombreForm) {
		this.nombreForm = nombreForm;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
