package com.dto;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.exception.ServiciosException;
import com.srv.ActCampoServicio;

@Named(value="actCampoMB")
@SessionScoped
public class ActCampoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ActCampoDTO> listDTO;
	
	public List<ActCampoDTO> getListDTO() {
		return listDTO;
	}

	public void setListDTO(List<ActCampoDTO> listDTO) {
		this.listDTO = listDTO;
	}

	private List<ActCampoDTO> filtroActCamp;

	@EJB
	private ActCampoServicio actCampoServicio;
	
	private ActCampoDTO actCampoDTO;
	
	
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
	
	@PostConstruct
	public void init() {
		listDTO = new ArrayList<ActCampoDTO>();
		setFiltroActCamp(new ArrayList<ActCampoDTO>());
		actCampoDTO = new ActCampoDTO();
		
	}
	
	public void listAct() throws ServiciosException {
		listDTO = actCampoServicio.listarActividades();
		
	}
}