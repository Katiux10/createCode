package rest;

import java.io.Serializable;

public class ActCampoDTOrest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long idActCampo;
	
	private long idUsuario;
	
	private long idFormulario;
	
	private String fecha;
	
	private String hora;
	
	private String departamento;

	public ActCampoDTOrest() {
		super();
	}

	public ActCampoDTOrest(long idActCampo, long idUsuario, long idFormulario, String fecha, String hora,
			String departamento) {
		super();
		this.idActCampo = idActCampo;
		this.idUsuario = idUsuario;
		this.idFormulario = idFormulario;
		this.fecha = fecha;
		this.hora = hora;
		this.departamento = departamento;
	}

	public long getIdActCampo() {
		return idActCampo;
	}

	public void setIdActCampo(long idActCampo) {
		this.idActCampo = idActCampo;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long usuario) {
		this.idUsuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(long idFormulario) {
		this.idFormulario = idFormulario;
	}
}