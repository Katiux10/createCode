package com.dto;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.exception.ServiciosException;
import com.srv.FormularioServicio;
import com.srv.LoginServicio;

@Named(value="formularioMB")
@SessionScoped
public class FormularioMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FormularioDTO formularioDTO;
	
	@EJB
	private FormularioServicio formularioServ;
	
	@EJB
	private LoginServicio loginServicio;

	public FormularioDTO getFormularioDTO() {
		return formularioDTO;
	}

	public FormularioServicio getFormularioServ() {
		return formularioServ;
	}

	public void setFormularioDTO(FormularioDTO formularioDTO) {
		this.formularioDTO = formularioDTO;
	}

	public void setFormularioServ(FormularioServicio formularioServ) {
		this.formularioServ = formularioServ;
	}
	
	@PostConstruct
	public void init() {
		formularioDTO = new FormularioDTO();
	}
	
	public String crearFormulario() throws ServiciosException{
		formularioDTO = formularioServ.crearForm(formularioDTO);
		
		if(formularioDTO.getIdFormulario() <= 0) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el formulario", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
		}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formulario creado con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		
		}
		
		return "gestionFormulario.xhtml";
		
	}
	
}
