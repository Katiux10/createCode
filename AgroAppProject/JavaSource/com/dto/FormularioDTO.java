package com.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.entities.Casilla;
import com.entities.Usuario;

public class FormularioDTO {
	
	private List<FormularioDTO> listaFormularioDTO;
	
	public List<FormularioDTO> getListaFormularioDTO() {
		return listaFormularioDTO;
	}

	public void setListaFormularioDTO(List<FormularioDTO> listaFormularioDTO) {
		this.listaFormularioDTO = listaFormularioDTO;
	}

	private long idFormulario;
	
	@NotNull
	private String nombreForm;
	
	private String resumen;
	
	private Usuario usuario;
	
	private List<Casilla> casillas;

	public FormularioDTO() {
		super();
	}
	
	public FormularioDTO(long idFormulario, @NotNull String nombreForm, String resumen, Usuario usuario,
			List<Casilla> casillas) {
		super();
		this.idFormulario = idFormulario;
		this.nombreForm = nombreForm;
		this.resumen = resumen;
		this.usuario = usuario;
		this.casillas = casillas;
	}
	
	public FormularioDTO(long idFormulario, @NotNull String nombreForm, String resumen, Usuario usuario) {
		super();
		this.idFormulario = idFormulario;
		this.nombreForm = nombreForm;
		this.resumen = resumen;
		this.usuario = usuario;
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

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}
}