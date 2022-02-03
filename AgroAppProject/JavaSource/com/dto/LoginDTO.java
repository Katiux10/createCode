package com.dto;

import javax.validation.constraints.NotNull;


public class LoginDTO {
	
	@NotNull	
	private String nombreUs;
	@NotNull
	private String contrasena;
		
	public LoginDTO() {
		super();
	}

	public String getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}