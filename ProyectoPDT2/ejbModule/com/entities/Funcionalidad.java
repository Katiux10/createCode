package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FUNCIONALIDADES database table.
 * 
 */
@Entity
@Table(name="FUNCIONALIDADES")
@NamedQuery(name="Funcionalidad.findAll", query="SELECT f FROM Funcionalidad f")
public class Funcionalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_FUNCION", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_FUNCION")
	@Column(name="ID_FUNCION", unique=true, nullable=false, precision=38)
	private long idFuncion;
	
	@Column(length=100)
	private String descripcion;
	
	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-many association to Rol
	@ManyToMany(mappedBy="funcionalidades")
	private List<Rol> roles;

	public Funcionalidad() {
	}
	
	public Funcionalidad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public long getIdFuncion() {
		return this.idFuncion;
	}

	public void setIdFuncion(long idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}