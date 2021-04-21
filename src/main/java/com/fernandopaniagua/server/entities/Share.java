package com.fernandopaniagua.server.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gasto")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "idpropietario")
	private int idPropietario;
	@Column(name = "idusuario")
	private int idUsuario;
	@Column
	private int importe;
	@Column
	private String descripcion;
	@Column
	private String fecha;
	@OneToOne
	@JoinColumn(name = "idpropietario", insertable = false, updatable = false)
	private User usuario;
	
	public Share() {
		super();
	}

	public Share(int idPropietario, int idUsuario, int importe, String descripcion, String fecha) {
		super();
		this.idPropietario = idPropietario;
		this.idUsuario = idUsuario;
		this.importe = importe;
		this.descripcion = descripcion;
		this.fecha = fecha;
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
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getUsuario() {
		return usuario;
	}
	
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
}
