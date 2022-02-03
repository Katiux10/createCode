package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.dto.ActCampoDTO;
import com.exception.ServiciosException;
import com.srv.ActCampoServicio;


@Stateless
@LocalBean
public class ActCampoRestServ implements IActCampoRest{
	
	@EJB
	private ActCampoServicio actCampoServ;
	
	@Override
    public String echo() {
        return "Servicio Actividad de campo disponible";
    }
	
	@Override
	public Response crearActCampo(ActCampoDTOrest actCampRest){
		try {
			actCampoServ.crearAct(actCampRest);
			return Response.ok().entity("Se creó la actividad de campo ").build();
		} catch (ServiciosException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No se pudo crear la actividad").build();
		}
	}

	@Override
	public Response obtenerActCampo() {
		try {
			List<ActCampoDTO> listActivities = actCampoServ.listarActividades();
			return Response.ok().entity(listActivities).build();
		} catch(ServiciosException e) {
			return Response.serverError().build();
		}
	} 

}