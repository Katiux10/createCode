package com.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import com.entities.Casilla;
import com.entities.Formulario;
import com.exception.ServiciosException;

@Remote
public interface FormularioBeanRemote {
	
	public void crearFormulario(Formulario formulario) throws ServiciosException;
	public void modificarFormulario(Formulario formulario) throws ServiciosException;
	public List<Formulario> listarTodosFormularios() throws ServiciosException;
	public void asignarCasilla(Long idCasilla, Long idFormulario) throws ServiciosException;
	public void asignarCasillaOb(Long idCasilla, Long idFormulario) throws ServiciosException;
	public Formulario buscarFormularioNom(String nombreFormulario) throws ServiciosException;
	public Formulario buscarFormularioId(long idFormulario) throws ServiciosException;
	public ArrayList<Casilla> listCasForm(Long idFormulario) throws ServiciosException;
	public void eliminarCasillaForm(Long idCasilla, Long idFormulario) throws ServiciosException;

}


