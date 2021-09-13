package com.dto;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.entities.Rol;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.srv.LoginServicio;


@Named(value="loginMB")
@SessionScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginDTO loginDTO;
	
	@EJB
	LoginServicio loginServ;
	
	private Usuario usLog;

	public LoginMB() {
		super();
	}
	
	//Getters and Setters

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public LoginServicio getLoginServ() {
		return loginServ;
	}

	public void setLoginServ(LoginServicio loginServ) {
		this.loginServ = loginServ;
	}
	
	public Usuario getUsLog() {
		return usLog;
	}

	public void setUsLog(Usuario usLog) {
		this.usLog = usLog;
	}

	@PostConstruct
	public void init() {
		loginDTO = new LoginDTO();
		usLog = null;
	}
	
	public String buscarUsuarioLogin() throws ServiciosException {
		usLog = loginServ.login(loginDTO.getNombreUs(), loginDTO.getContrasena());
		Rol rolUs = usLog.getRol();
		
		if (rolUs.getNombre().equals("Común") || rolUs.getNombre().equals("Experto")){
			
			return "homeComExp.xhtml";
		}else {
			
			return "home.xhtml";
		}
		 
	}
}
