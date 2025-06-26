package com.sharp.clinica_solar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SoliElemento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSoliElemento;

	@ManyToOne(optional = false)
	private SoliCompra solicitud;

	@ManyToOne(optional = false)
	private Elemento elemento;

	@Column(nullable = false)
	private int cantSolicitada;

	@ManyToOne
	private PrecioProveedor precioSeleccionado;
	
	public SoliElemento() {
		super();
	}

	public SoliElemento(Integer idSoliElemento, SoliCompra solicitud, Elemento elemento, int cantSolicitada) {
		super();
		this.idSoliElemento = idSoliElemento;
		this.solicitud = solicitud;
		this.elemento = elemento;
		this.cantSolicitada = cantSolicitada;
	}

	public PrecioProveedor getPrecioSeleccionado() {
	    return precioSeleccionado;
	}

	public void setPrecioSeleccionado(PrecioProveedor precioSeleccionado) {
	    this.precioSeleccionado = precioSeleccionado;
	}
	
	public Integer getIdSoliElemento() {
		return idSoliElemento;
	}

	public void setIdSoliElemento(Integer idSoliElemento) {
		this.idSoliElemento = idSoliElemento;
	}

	public SoliCompra getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SoliCompra solicitud) {
		this.solicitud = solicitud;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public int getCantSolicitada() {
		return cantSolicitada;
	}

	public void setCantSolicitada(int cantSolicitada) {
		this.cantSolicitada = cantSolicitada;
	}

}
