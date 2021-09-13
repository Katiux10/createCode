package com.entities;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATOS database table.
 * 
 */
@Entity
@Table(name="DATOS")
@NamedQuery(name="Dato.findAll", query="SELECT d FROM Dato d")
public class Dato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_DATOS", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DATOS")
	@Column(name="ID_DATOS")
	private long idDato;

	private String valor;

	//bi-directional many-to-one association to Casilla
	@ManyToOne
	@JoinColumn(name="ID_CASILLA")
	private Casilla casilla;

	public Dato() {
	}

	public Dato(String valor, Casilla casilla) {
		super();
		this.valor = valor;
		this.casilla = casilla;
	}

	public long getIdDato() {
		return this.idDato;
	}

	public void setIdDato(long idDato) {
		this.idDato = idDato;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Casilla getCasilla() {
		return this.casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}
}