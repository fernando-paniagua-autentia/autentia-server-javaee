package com.fernandopaniagua.server.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name = "idpropietario")
	private int idPropietario;
	@Column(name = "idamigo")
	private int idAmigo;
	
	public Group() {
		super();
	}

	public Group(int idPropietario, int idAmigo) {
		super();
		this.idPropietario = idPropietario;
		this.idAmigo = idAmigo;
	}
	public Group(int id, int idPropietario, int idAmigo) {
		super();
		this.id = id;
		this.idPropietario = idPropietario;
		this.idAmigo = idAmigo;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public int getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(int idAmigo) {
		this.idAmigo = idAmigo;
	}
	
}
