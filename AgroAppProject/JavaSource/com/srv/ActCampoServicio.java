package com.srv;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOS.DAOActividadDeCampo;
import com.DAOS.DAOFormulario;
import com.DAOS.DAOUsuario;
import com.dto.ActCampoDTO;
import com.entities.ActividadDeCampo;
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

		public List<ActCampoDTO> listarActividades() throws ServiciosException {
			List<ActCampoDTO> listAGRDTO = new ArrayList<ActCampoDTO>(); 
			List<ActividadDeCampo> listAGR = daoActividadDeCampo.listActividad();
			for(ActividadDeCampo a: listAGR ) {			
				listAGRDTO.add(toDTO(a));
			}
			return listAGRDTO;
		}
		
		public ActCampoDTO toDTO(ActividadDeCampo actDeCamp) throws ServiciosException{
			 
			ActCampoDTO actDTO = new ActCampoDTO();
			actDTO.setIdActCampo(actDeCamp.getIdActCampo());
			actDTO.setUsuario(actDeCamp.getUsuario().getNombreUsuario());
			actDTO.setFecha(Fecha.dateToString(actDeCamp.getFecha()));
			actDTO.setHora(actDeCamp.getHora());
			actDTO.setDepartamento(actDeCamp.getDepartamento());
			actDTO.setRolUs(actDeCamp.getUsuario().getRol().getNombre());
			actDTO.setUsuario(actDeCamp.getUsuario().getNombreUsuario());
			actDTO.setMetMuestreo(actDeCamp.getDatos().get(0).getValor());
			actDTO.setEstMuestreo(actDeCamp.getDatos().get(1).getValor());
			actDTO.setLatitud(actDeCamp.getDatos().get(2).getValor());
			actDTO.setLongitud(actDeCamp.getDatos().get(3).getValor());
			actDTO.setCantidad(actDeCamp.getDatos().get(4).getValor());
			
			return actDTO;
		}
		
		//para usar en rest
		public ActCampoDTOrest toRestDTO(ActividadDeCampo act) throws ServiciosException{
			 
			ActCampoDTOrest actDTO = new ActCampoDTOrest();
			actDTO.setIdActCampo(act.getIdActCampo());
			actDTO.setIdUsuario(act.getUsuario().getIdUsuario());
			actDTO.setFecha(Fecha.dateToString(act.getFecha()));
			actDTO.setHora(act.getHora());
			actDTO.setDepartamento(act.getDepartamento());
			actDTO.setIdFormulario(act.getFormulario());
			
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