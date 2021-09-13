package com.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOS.DAOCasilla;
import com.entities.Casilla;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CasillaBean
 */
@Stateless
public class CasillaBean implements CasillaBeanRemote {

    /**
     * Default constructor. 
     */
	@EJB
	private DAOCasilla daoCasilla;
    public CasillaBean() {
        
    }
	@Override
	public void crearCasilla(Casilla casilla) throws ServiciosException {
		daoCasilla.crearCasilla(casilla);
	}
	@Override
	public Casilla buscarCasillaId(long idCasilla) throws ServiciosException{
		return daoCasilla.buscarCasillaId(idCasilla);
	}
	@Override
	public List<Casilla> listarCasillas() throws ServiciosException {
		return daoCasilla.listarCasillas();
	}
	@Override
	public Casilla buscarCasParametro(String parametro) throws ServiciosException{
		return daoCasilla.buscarCasParametro(parametro);
	}

}
