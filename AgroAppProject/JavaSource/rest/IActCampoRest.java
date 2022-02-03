package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public interface IActCampoRest {
	
	@GET
    @Path("echo")
    @Produces({MediaType.TEXT_PLAIN})
    public String echo();
	
	@POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("crear")
	public Response crearActCampo(ActCampoDTOrest crearActCampRest);
	
	@GET 
	@Path("obtenerActCampo")
    @Produces(MediaType.APPLICATION_JSON)
	public Response obtenerActCampo();

}