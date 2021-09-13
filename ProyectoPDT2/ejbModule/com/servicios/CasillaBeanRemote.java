package com.servicios;

import java.util.List;

import javax.ejb.Remote;
import com.entities.Casilla;
import com.exception.ServiciosException;

@Remote
public interface CasillaBeanRemote {
	public void crearCasilla(Casilla casilla) throws ServiciosException;
	public Casilla buscarCasillaId(long idCasilla) throws ServiciosException;
	public List<Casilla> listarCasillas() throws ServiciosException;
	public Casilla buscarCasParametro(String parametro) throws ServiciosException;
}
