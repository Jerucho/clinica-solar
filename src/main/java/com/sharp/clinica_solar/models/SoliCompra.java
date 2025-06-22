package com.sharp.clinica_solar.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SoliCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSolicitud;

	@ManyToOne(optional = false)
	private Usuario usuario;

	@Column(nullable = false, columnDefinition = "CHAR(1)")
	private String statusSolicitud = "P";

	@Column(nullable = false)
	private LocalDateTime fechaCreacion;

	@OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SoliElemento> elementos = new ArrayList<>();
	
	public SoliCompra() {
		super();
	}
	
	public SoliCompra(Integer idSolicitud, Usuario usuario, String statusSolicitud, LocalDateTime fechaCreacion,
			List<SoliElemento> elementos) {
		super();
		this.idSolicitud = idSolicitud;
		this.usuario = usuario;
		this.statusSolicitud = statusSolicitud;
		this.fechaCreacion = fechaCreacion;
		this.elementos = elementos;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getStatusSolicitud() {
		return statusSolicitud;
	}

	public void setStatusSolicitud(String statusSolicitud) {
		this.statusSolicitud = statusSolicitud;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<SoliElemento> getElementos() {
		return elementos;
	}

	public void setElementos(List<SoliElemento> elementos) {
		this.elementos = elementos;
	}
	
	public int getCantidadTotalInsumos() {
	    return elementos.stream()
	        .mapToInt(SoliElemento::getCantSolicitada)
	        .sum();
	}

}
