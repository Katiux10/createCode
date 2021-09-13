package com.entities;
import java.io.Serializable;
import javax.persistence.*;


import java.util.List;
/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SEQ_Usuario", initialValue = 1, allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "SEQ_Usuario")
	@Column(name="ID_Usuario")
	private long idUsuario;

	@Column(name="NOMBRE_USUARIO", unique=true, nullable=false, length=50)
	private String nombreUsuario;
	
	@Column(nullable=false, length=20)
	private String apellido;

	private String cedula;
	
	@Column(nullable=false, length=50)
	private String contrasena;
	
	@Column(nullable=false, length=50)
	private String email;
	
	@Column(length=50)
	private String instituto;
	
	@Column(nullable=false, length=20)
	private String nombre;
	
	@Column(length=50)
	private String profesion;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="usuario")
	private List<Formulario> formularios;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="ID_ROL", nullable=false)
	private Rol rol;

	public Usuario() {
	}
	
	public Usuario(String nombreUsuario, String apellido, String cedula, String contrasena, String email,
			String instituto, String nombre, String profesion, Rol rol) {
		super();

		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.cedula = cedula;
		this.contrasena = contrasena;
		this.email = email;
		this.instituto = instituto;
		this.nombre = nombre;
		this.profesion = profesion;
		this.rol = rol;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstituto() {
		return this.instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public List<Formulario> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}
	
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public Formulario addFormulario(Formulario formulario) {
		getFormularios().add(formulario);
		formulario.setUsuario(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setUsuario(null);

		return formulario;
	}


}