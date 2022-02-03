package com.dto;

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
	
	private List<ActCampoDTO> listAct;
	
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

	public List<ActCampoDTO> getListAct() {
		return listAct;
	}

	public void setListAct(List<ActCampoDTO> listAct) {
		this.listAct = listAct;
	}
	
	public List<ActCampoDTO> getFiltroActCamp() {
		return filtroActCamp;
	}

	public void setFiltroActCamp(List<ActCampoDTO> filtroActCamp) {
		this.filtroActCamp = filtroActCamp;
	}
	
	@PostConstruct
	public void init() {
		listAct = new ArrayList<ActCampoDTO>();
		setFiltroActCamp(new ArrayList<ActCampoDTO>());
		actCampoDTO = new ActCampoDTO();
		
	}
	
	public void listAct() throws ServiciosException {
		listAct = actCampoServicio.listarActividades();
		
	}
	
}