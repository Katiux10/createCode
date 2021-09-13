package com.servicios;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Dato;
import com.exception.ServiciosException;

@Remote
public interface DatoBeanRemote {
	public void crearDato(Dato dato) throws ServiciosException;
	public List<Dato> buscarDatoId(long idDato) throws ServiciosException;

}
