package com.sharp.clinica_solar.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Presupuesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPresupuesto;

	private int mesPresupuesto;

	private int anioPresupeusto;

	@Column(nullable = false)
	private BigDecimal montoPresupuesto = BigDecimal.ZERO;

	@Column(nullable = false)
	private BigDecimal gastadoPresupuesto = BigDecimal.ZERO;

	public Presupuesto() {
		super();

	}
	
	public Presupuesto(Integer idPresupuesto, int mesPresupuesto, int anioPresupeusto, BigDecimal montoPresupuesto,
			BigDecimal gastadoPresupuesto) {
		super();
		this.idPresupuesto = idPresupuesto;
		this.mesPresupuesto = mesPresupuesto;
		this.anioPresupeusto = anioPresupeusto;
		this.montoPresupuesto = montoPresupuesto;
		this.gastadoPresupuesto = gastadoPresupuesto;
	}

	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	public int getMesPresupuesto() {
		return mesPresupuesto;
	}

	public void setMesPresupuesto(int mesPresupuesto) {
		this.mesPresupuesto = mesPresupuesto;
	}

	public int getAnioPresupeusto() {
		return anioPresupeusto;
	}

	public void setAnioPresupeusto(int anioPresupeusto) {
		this.anioPresupeusto = anioPresupeusto;
	}

	public BigDecimal getMontoPresupuesto() {
		return montoPresupuesto;
	}

	public void setMontoPresupuesto(BigDecimal montoPresupuesto) {
		this.montoPresupuesto = montoPresupuesto;
	}

	public BigDecimal getGastadoPresupuesto() {
		return gastadoPresupuesto;
	}

	public void setGastadoPresupuesto(BigDecimal gastadoPresupuesto) {
		this.gastadoPresupuesto = gastadoPresupuesto;
	}
	
	
}
