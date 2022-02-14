package com.dto;

import java.time.LocalDate;
import java.util.List;
import com.entities.Dato;

public class ActCampoDTO {
	
	private long idActCampo;
	
	private String usuario;
	
	private String rolUs;
	
	private String fecha;
	
	private LocalDate fechaIni;
	
	private LocalDate fechaFin;
	
	private String hora;
	
	private String departamento;
	
	private String metMuestreo;
	
	private String estMuestreo;
	
	private String latitud;
	
	private String longitud;
	
	private String cantidad;
	
	private List<Dato> datos;
	

	public ActCampoDTO() {
		super();
	}

	public long getIdActCampo() {
		return idActCampo;
	}

	public void setIdActCampo(long idActCampo) {
		this.idActCampo = idActCampo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	public String getMetMuestreo() {
		return metMuestreo;
	}

	public void setMetMuestreo(String metMuestreo) {
		this.metMuestreo = metMuestreo;
	}

	public String getEstMuestreo() {
		return estMuestreo;
	}

	public void setEstMuestreo(String estMuestreo) {
		this.estMuestreo = estMuestreo;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public List<Dato> getDatos() {
		return datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	public LocalDate getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getRolUs() {
		return rolUs;
	}

	public void setRolUs(String rolUs) {
		this.rolUs = rolUs;
	}
}