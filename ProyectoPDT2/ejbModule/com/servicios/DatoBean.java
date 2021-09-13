package com.servicios;


import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOS.DAODato;
import com.entities.Dato;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class DatoBean
 */
@Stateless
public class DatoBean implements DatoBeanRemote {

    @EJB
	private DAODato daoDato;
    public DatoBean() {
    	
    }
    public void crearDato(Dato dato) throws ServiciosException{
		daoDato.crearDato(dato);
	}
    public List<Dato> buscarDatoId(long idDato) throws ServiciosException{
    	return daoDato.buscarDatoId(idDato);
    }
}
