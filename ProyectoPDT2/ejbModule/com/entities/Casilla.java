package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name="CASILLAS")
@NamedQuery(name="Casilla.findAll", query="SELECT c FROM Casilla c")
public class Casilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_CASILLA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CASILLA")
	@Column(name="ID_CASILLA")
	private long idCasilla;

	private String descripcion;

	private String parametro;
	
	@Enumerated(EnumType.STRING)
	private TipoValor tipoValor;
	
	@Column(name="UNIDAD_MEDIDA")
	private String unidadMedida;

	//bi-directional many-to-one association to Dato
	@OneToMany(mappedBy="casilla")
	private List<Dato> datos;

	//bi-directional many-to-many association to Formulario
	@ManyToMany(mappedBy="casillas")
	private List<Formulario> formularios;


	public Casilla() {
	}
	
	public Casilla(String descripcion, String parametro, TipoValor tipoValor, String unidadMedida, List<Dato> datos,
			List<Formulario> formularios) {
		super();
		this.descripcion = descripcion;
		this.parametro = parametro;
		this.tipoValor = tipoValor;
		this.unidadMedida = unidadMedida;
		this.datos = datos;
		this.formularios = formularios;
	}

	public Casilla(String descripcion, String parametro, TipoValor tipoValor, String unidadMedida) {
		super();
		this.descripcion = descripcion;
		this.parametro = parametro;
		this.tipoValor = tipoValor;
		this.unidadMedida = unidadMedida;
	}
	
	public long getIdCasilla() {
		return this.idCasilla;
	}

	public void setIdCasilla(long idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public List<Dato> getDatos() {
		return this.datos;
	}

	public void setDatos(List<Dato> datos) {
		this.datos = datos;
	}

	public Dato addDato(Dato dato) {
		getDatos().add(dato);
		dato.setCasilla(this);

		return dato;
	}

	public Dato removeDato(Dato dato) {
		getDatos().remove(dato);
		dato.setCasilla(null);

		return dato;
	}

	public List<Formulario> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public TipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(TipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}
}