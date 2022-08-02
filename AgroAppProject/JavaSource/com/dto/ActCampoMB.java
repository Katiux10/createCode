package com.dto;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.entities.Rol;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.srv.ActCampoServicio;

@Named(value="actCampoMB")
@ViewScoped
public class ActCampoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ActCampoDTO> listDTO;
	private List<ActCampoDTO> filtroActCamp;
	private List<FormularioDTO> formDTO;
	@EJB
	private ActCampoServicio actCampoServicio;
	private ActCampoDTO actCampoDTO;
	private Date fechaIni;
	private Date fechaFin;
	

	public ActCampoMB() {
		super();
	}
	
	//Getters and Setters

	public ActCampoDTO getActCampoDTO() {
		return actCampoDTO;
	}

	public void setActCampoDTO(ActCampoDTO actCampoDTO) {
		this.actCampoDTO = actCampoDTO;
	}
	
	public List<ActCampoDTO> getFiltroActCamp() {
		return filtroActCamp;
	}

	public void setFiltroActCamp(List<ActCampoDTO> filtroActCamp) {
		this.filtroActCamp = filtroActCamp;
	}
	
	public List<ActCampoDTO> getListDTO() {
		return listDTO;
	}

	public void setListDTO(List<ActCampoDTO> listDTO) {
		this.listDTO = listDTO;
	}
	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@PostConstruct
	public void init() {
		listDTO = new ArrayList<ActCampoDTO>();
		filtroActCamp = new ArrayList<ActCampoDTO>();
		actCampoDTO = new ActCampoDTO();
		fechaIni = null;
		fechaFin = null;
		formDTO = new ArrayList<FormularioDTO>();

	}
	
	public void listAct() throws ServiciosException {
			listDTO = actCampoServicio.listarActividades();
			
	}
	
	public List<ActCampoDTO> filtroFecha(Date ini, Date fin) {
		try {
			listDTO = actCampoServicio.listarActFiltro(ini, fin);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		for (ActCampoDTO ac : listDTO) {
			if (fechaIni.after(ini) && fechaFin.before(fin)) {
				filtroActCamp.add(ac);
			}
		}
		return filtroActCamp;
	}
}