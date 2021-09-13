package com.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOS.DAOFormulario;
import com.entities.Casilla;
import com.entities.Formulario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FormularioBean
 */
@Stateless
public class FormularioBean implements FormularioBeanRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	private DAOFormulario daoFormulario;
    public FormularioBean() {
        
    }

	@Override
	public void crearFormulario(Formulario formulario) throws ServiciosException {
		daoFormulario.crearFormulario(formulario);
	}
	
	@Override
	public List<Formulario> listarTodosFormularios() throws ServiciosException {
		return daoFormulario.listarTodosFormularios();
	}
	
	@Override
	public void asignarCasilla(Long idCasilla, Long idFormulario) throws ServiciosException{
		daoFormulario.asignarCasilla(idCasilla, idFormulario);
	}
	
	@Override
	public void asignarCasillaOb(Long idCasilla, Long idFormulario) throws ServiciosException{
		daoFormulario.asignarCasillaOb(idCasilla, idFormulario);
	}

	@Override
	public void eliminarCasillaForm(Long idCasilla, Long idFormulario) throws ServiciosException{
		daoFormulario.eliminarCasillaForm(idCasilla, idFormulario);
	}

	@Override
	public void modificarFormulario(Formulario formulario) throws ServiciosException {
		daoFormulario.modificarFormulario(formulario);
	}
	
	@Override
	public Formulario buscarFormularioNom(String nombreFormulario) throws ServiciosException {
		return daoFormulario.buscarFormularioNom(nombreFormulario);
		
	}
	
	@Override
	public Formulario buscarFormularioId(long idFormulario) throws ServiciosException {
		return daoFormulario.buscarFormularioId(idFormulario);
	}
	
	@Override
	public ArrayList<Casilla> listCasForm(Long idFormulario) throws ServiciosException {
		return daoFormulario.listCasForm(idFormulario);
	}
}
