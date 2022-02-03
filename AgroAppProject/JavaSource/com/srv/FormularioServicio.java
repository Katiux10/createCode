package com.srv;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.DAOS.DAOFormulario;
import com.dto.FormularioDTO;
import com.entities.Formulario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class FormularioServicio {

	@EJB
	private DAOFormulario daoFormulario;

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
				
		return formDTO;
	}
	
	public Formulario toForm(FormularioDTO formDTO) throws ServiciosException {
		
		Formulario form = new Formulario();
		form.setNombreFormulario(formDTO.getNombreForm());
		form.setResumen(formDTO.getResumen());
		form.setIdFormulario(formDTO.getIdFormulario());
			
		return form;
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