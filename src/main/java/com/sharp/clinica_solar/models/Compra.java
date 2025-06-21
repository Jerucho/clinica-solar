package com.sharp.clinica_solar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompra;

	@ManyToOne(optional = false)
	private SoliCompra solicitud;
	
	public Compra() {
		super();
	}
	
	public Compra(Integer idCompra, SoliCompra solicitud) {
		super();
		this.idCompra = idCompra;
		this.solicitud = solicitud;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public SoliCompra getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SoliCompra solicitud) {
		this.solicitud = solicitud;
	}
	
	
}
