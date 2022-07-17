package com.dto;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpSession;

import com.entities.Rol;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.srv.LoginServicio;

import conexion.ADAuthenticator;


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
	private String msg;

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
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@PostConstruct
	public void init() {
		loginDTO = new LoginDTO();
		//usLog = null;
	}
	
	public String logout() {
		usLog = null;
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout correcto.", "");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		return "login.xhtml";
	}
	
	//esto para que se vaya por un login o el otro, si tiene @ va por el loginAD
	public String loginGenerico() throws ServiciosException {
		if (loginDTO.getNombreUs().contains("@")) {
			return loginAD();
		} else {
			return buscarUsuarioLogin();
		}
	}

	@SuppressWarnings("unused")
	public String loginAD() {
		// obtenemos el dominio en base al email provisto
		try {
			ADAuthenticator userAD = new ADAuthenticator();
			//los controles
			if (userAD != null) {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuario encontrado en el AD " + loginDTO.getNombreUs(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				return "home.xhtml";// esta es la pagina que queremos que redirija
				
			} else {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no encontrado en el AD, o contraseña incorrecta",
						"");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	
			}

		} catch (Exception e) {
			// Failed to authenticate user!
			System.out.println(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no encontrado", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
		}
		return "";
	}

	public String buscarUsuarioLogin() throws ServiciosException {
		try {
			usLog = loginServ.login(loginDTO.getNombreUs(), loginDTO.getContrasena());
			Rol rolUs = usLog.getRol();
		
			if((usLog == null) || !(usLog == loginServ.login(loginDTO.getNombreUs(), loginDTO.getContrasena()))) {	
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar usuario y/o contraseña", "");
				FacesContext.getCurrentInstance().addMessage(null,facesMsg);
				
				if (rolUs.getNombre().equals("Común")){
					return "homeComun.xhtml";
				}
				if(rolUs.getNombre().equals("Experto")) {
					return "homeExperto.xhtml";
				}else {
					return "home.xhtml";
				}
			}else {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.FACES_MESSAGES, "Usuario logueado con éxito");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		}catch(Exception e) {
			// Failed to authenticate user!
			System.out.println(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario no encontrado", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
		}
		return null;
	}
}