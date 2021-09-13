package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ACTIVIDADES_DE_CAMPO database table.
 * 
 */
@Entity
@Table(name="ACTIVIDADES_DE_CAMPO")
@NamedQuery(name="ActividadDeCampo.findAll", query="SELECT a FROM ActividadDeCampo a")
public class ActividadDeCampo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ACT_CAMPO", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ACT_CAMPO")
	@Column(name="ID_ACT_CAMPO")
	private long idActCampo;

	//bi-directional many-to-one association to Formulario
	@JoinColumn(name="ID_FORMULARIO")
	private long formulario;
	
	//bi-directional one-to-many association to Dato
		@JoinTable(
			name="ACT_DATOS"
			, joinColumns={
				@JoinColumn(name="ID_ACT_CAMPO")
				}
			, inverseJoinColumns={
				@JoinColumn(name="ID_DATO", nullable=false)
				}
			)

	@OneToMany(cascade = CascadeType.ALL)
	private List<Dato> datos;
	
	@ManyToOne
	private Usuario usuario;
	
	@Column
	private LocalDate Fecha;
	
	@Column
	private String Hora;
	
	private String departamento;

	public ActividadDeCampo() {
	}
	
	public ActividadDeCampo(long formulario, Usuario usuario, LocalDate fecha, String hora ) {
		super();
		this.formulario = formulario;
		this.usuario = usuario;
		this.Fecha = fecha;
		this.Hora = hora;
	}
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public String getHora() {
		return Hora;
	}
	
	public void setHora(String hora) {
		Hora = hora;
	}

	public long getIdActCampo() {
		return this.idActCampo;
	}

	public void setIdActCampo(long idActCampo) {
		this.idActCampo = idActCampo;
	}

	public long getFormulario() {
		return this.formulario;
	}

	public void setFormulario(long formulario) {
		this.formulario = formulario;
	}

	public List<Dato> getDatos() {
		return this.datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

}