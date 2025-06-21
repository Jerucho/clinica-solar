package com.sharp.clinica_solar.DTOs;

public class SolicitudInsumoDTO {
	private String nombre;
	private int cantidad;
	private Long idElemento;

	public SolicitudInsumoDTO() {
		super();
	}

	public SolicitudInsumoDTO(String nombre, int cantidad, Long idElemento) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.idElemento = idElemento;
	}

	public Long getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}