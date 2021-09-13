package rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.dto.LoginDTO;
import com.dto.UsuarioDTO;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.srv.UsuarioServicio;
import com.srv.LoginServicio;

@Stateless
@LocalBean
public class UsuarioRest implements IUsuarioRest{
	
	@Inject
	private UsuarioServicio usuarioServicio;
	
	@Inject
	private LoginServicio LoginServicio;
	
	@Override
    public String echo() {
        return "Servicio Usuarios Disponible";
    }
	
	@Override
	public Response listUsuarios(){
		try {
			List<UsuarioDTO> usuarios = usuarioServicio.list();
			return Response.ok().entity(usuarios).build();
		} catch (ServiciosException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	@Override
	public Response login(LoginDTO loginDTO){
		try {
			Usuario usuario = LoginServicio.login(loginDTO.getNombreUs(),loginDTO.getContrasena());
			if(usuario.getNombreUsuario() != null || usuario.getContrasena() != null){
				return Response.ok().entity("Bienvenido " + usuario.getNombre()).build();
			}
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Usuario o contraseña inválida").build();
		}
		return Response.ok().entity("Usuario logueado con éxito").build();
	}

}