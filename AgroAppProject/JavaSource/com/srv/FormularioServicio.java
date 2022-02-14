package com.srv;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.DAOS.DAOFormulario;
import com.dto.FormularioDTO;
import com.dto.LoginMB;
import com.dto.UsuarioDTO;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class FormularioServicio {

	@EJB
	private DAOFormulario daoFormulario;
	
	@Inject
	private LoginMB loginMB;
	//private LoginServicio loginServ;

	public DAOFormulario getDaoFormulario() {
		return daoFormulario;
	}

	public void setDaoFormulario(DAOFormulario daoFormulario) {
		this.daoFormulario = daoFormulario;
	}
	
	public FormularioDTO toFormDTO(Formulario form) throws ServiciosException {
		
		FormularioDTO formDTO = new FormularioDTO();
		formDTO.setNombreForm(form.getNombreFormulario());
		formDTO.setResumen(form.getResumen());
		formDTO.setIdFormulario(form.getIdFormulario());
		formDTO.setUsuario(loginMB.getUsLog());
				
		return formDTO;
	}
	
	public Formulario toForm(FormularioDTO formDTO) throws ServiciosException {
		
		Formulario form = new Formulario();
		form.setNombreFormulario(formDTO.getNombreForm());
		form.setResumen(formDTO.getResumen());
		form.setIdFormulario(formDTO.getIdFormulario());
		form.setUsuario(loginMB.getUsLog());	
		
		return form;
	}
	
	public List<FormularioDTO> list() throws ServiciosException {
		List<FormularioDTO> listFormDTO = new ArrayList<FormularioDTO>(); 
		List<Formulario> listForm = daoFormulario.listarTodosFormularios();
		for(Formulario f: listForm ) {
			listFormDTO.add(toFormDTO(f));
		}
		return listFormDTO;
	}
	public FormularioDTO crearForm(FormularioDTO formularioDTO) throws ServiciosException {

		Formulario form = new Formulario();
		form = toForm(formularioDTO);//retorna el DTO convertido en formulario
			
		daoFormulario.crearFormulario(form);
		
		FormularioDTO fDTO = new FormularioDTO();//retorna el Formulario convertido a DTO
		fDTO = toFormDTO(form);

		return fDTO;
		
		
	}
}