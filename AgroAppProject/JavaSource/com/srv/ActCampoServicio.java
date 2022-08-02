package com.srv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.DAOS.DAOActividadDeCampo;
import com.DAOS.DAOFormulario;
import com.DAOS.DAOUsuario;
import com.dto.ActCampoDTO;
import com.dto.FormularioDTO;
import com.dto.LoginMB;
import com.dto.UsuarioDTO;
import com.entities.ActividadDeCampo;
import com.entities.Dato;
import com.entities.Usuario;
import com.exception.ServiciosException;
import rest.ActCampoDTOrest;
import tools.Fecha;

@Stateless
@LocalBean
public class ActCampoServicio {
	
	@EJB
	private DAOActividadDeCampo daoActividadDeCampo;
	@EJB
	private DAOUsuario daoUsuario;
	@EJB
	private DAOFormulario daoFormulario;
	
	public ArrayList<ActCampoDTO> listarActividades() throws ServiciosException {
		ArrayList<ActCampoDTO> listAGRDTO = new ArrayList<ActCampoDTO>();
		ArrayList<ActividadDeCampo> listAGR = daoActividadDeCampo.listActividad();
		for(ActividadDeCampo a: listAGR ) {			
			listAGRDTO.add(toDTO(a));
			
		}
		return listAGRDTO;
	}
	
	public ArrayList<ActCampoDTO> listarActFiltro(Date fecIni, Date fecFin) throws ServiciosException {
		ArrayList<ActCampoDTO> listAGRDTO = new ArrayList<ActCampoDTO>();
		List<ActividadDeCampo> listAGR = daoActividadDeCampo.buscarPorFecha(fecIni, fecFin);
		for(ActividadDeCampo a: listAGR ) {			
			listAGRDTO.add(toDTO(a));
			
		}
		return listAGRDTO;
	}

	public ActCampoDTO toDTO(ActividadDeCampo actDeCamp) throws ServiciosException{
		ActCampoDTO actDTO = new ActCampoDTO();
		try {
			actDTO.setIdActCampo(actDeCamp.getIdActCampo());
			actDTO.setUsuario(actDeCamp.getUsuario().getNombreUsuario());
			actDTO.setFormulario(actDeCamp.getFormulario());
			actDTO.setFecha(Fecha.dateToString(actDeCamp.getFecha()));
			actDTO.setHora(actDeCamp.getHora());
			actDTO.setDepartamento(actDeCamp.getDepartamento());
			actDTO.setRolUs(actDeCamp.getUsuario().getRol().getNombre());
			actDTO.setMetMuestreo(actDeCamp.getDatos().get(0).getValor());
			actDTO.setEstMuestreo(actDeCamp.getDatos().get(1).getValor());
			actDTO.setLatitud(actDeCamp.getDatos().get(2).getValor());
			actDTO.setLongitud(actDeCamp.getDatos().get(3).getValor());
			
			if(actDeCamp.getDatos().get(4).getValor() != null) {
				actDTO.setCantidad(actDeCamp.getDatos().get(4).getValor());
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actDTO;	

}
	//para usar en rest
	public ActCampoDTOrest toRestDTO(ActividadDeCampo act) throws ServiciosException{
		 
		ActCampoDTOrest actDTO = null;
		try {
			actDTO = new ActCampoDTOrest();
			actDTO.setIdActCampo(act.getIdActCampo());
			actDTO.setIdFormulario(act.getFormulario());
			actDTO.setIdUsuario(act.getUsuario().getIdUsuario());
			actDTO.setFecha(Fecha.dateToString(act.getFecha()));
			actDTO.setHora(act.getHora());
			actDTO.setDepartamento(act.getDepartamento());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return actDTO;
	}
	
	public ActividadDeCampo toAct(ActCampoDTOrest actDTO) throws ServiciosException{
		Usuario us = new Usuario();
		us = daoUsuario.buscarUsuario(actDTO.getIdUsuario());
		ActividadDeCampo act = new ActividadDeCampo();
		act.setIdActCampo(actDTO.getIdActCampo());
		act.setUsuario(us);
		act.setFecha(Fecha.stringToDate(actDTO.getFecha()));
		act.setHora(actDTO.getHora());
		act.setDepartamento(actDTO.getDepartamento());
		act.setFormulario(actDTO.getIdFormulario());
		return act;
	}
		
	public void crearAct(ActCampoDTOrest actDTO) throws ServiciosException {
		try {
			ActividadDeCampo act = new ActividadDeCampo();
			act = toAct(actDTO);
			daoActividadDeCampo.crearActDeCampo(act);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
			
	}	
}