package com.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the FORMULARIOS database table.
 * 
 */
@Entity
@Table(name="FORMULARIOS")
@NamedQuery(name="Formulario.findAll", query="SELECT f FROM Formulario f")
public class Formulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_FORMULARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_FORMULARIO")
	@Column(name="ID_FORMULARIO")
	private long idFormulario;

	@Column(name="NOMBRE_FORMULARIO", nullable=false, unique=true)
	private String nombreFormulario;
	
	@Column(name="RESUMEN", nullable=true)
	private String resumen;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to ActividadDeCampo
	@OneToMany
	private List<ActividadDeCampo> actividadesDeCampos;

	//bi-directional many-to-many association to Casilla
	@JoinTable(
		name="FORM_CASILLA"
		, joinColumns={
			@JoinColumn(name="ID_FORMULARIO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_CASILLA", nullable=false)
			}
		)
	@ManyToMany(cascade=CascadeType.MERGE)
	private List<Casilla> casillas;
	
	@Transient
	@JoinColumn(name="ID_CASILLA", nullable=false)
	private Casilla casilla;
	
	public Formulario() {
		
	}

	public Formulario(long idFormulario, String nombreFormulario, String resumen, Usuario usuario) {
		super();
		this.nombreFormulario = nombreFormulario;
		this.resumen = resumen;
		this.usuario = usuario;
	}
	
	public Formulario(String nombreFormulario, String resumen, Usuario usuario,  List<Casilla> casillas) {
		super();
		this.nombreFormulario = nombreFormulario;
		this.resumen = resumen;
		this.usuario = usuario;
		this.casillas = casillas;
	}
	
	public Formulario(String nombreFormulario, String resumen, Usuario usuario) {
		super();
		this.nombreFormulario = nombreFormulario;
		this.resumen = resumen;
		this.usuario = usuario;
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}
	
	public long getIdFormulario() {
		return this.idFormulario;
	}

	public void setIdFormulario(long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public String getNombreFormulario() {
		return this.nombreFormulario;
	}

	public void setNombreFormulario(String nombreFormulario) {
		this.nombreFormulario = nombreFormulario;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ActividadDeCampo> getActividadesDeCampos() {
		return this.actividadesDeCampos;
	}

	public void setActividadesDeCampos(List<ActividadDeCampo> actividadesDeCampos) {
		this.actividadesDeCampos = actividadesDeCampos;
	}

	public List<Casilla> getCasillas() {
		return this.casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}
}