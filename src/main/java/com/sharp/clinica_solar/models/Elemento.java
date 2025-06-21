package com.sharp.clinica_solar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Elemento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idElemento;

	@Column(length = 30, nullable = false)
	private String nomElemento;

	private Integer cantActual;

	
	public Elemento() {
		super();
	}
	
	public Elemento(Integer idElemento, String nomElemento, Integer cantActual) {
		super();
		this.idElemento = idElemento;
		this.nomElemento = nomElemento;
		this.cantActual = cantActual;
	}

	public Integer getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(Integer idElemento) {
		this.idElemento = idElemento;
	}

	public String getNomElemento() {
		return nomElemento;
	}

	public void setNomElemento(String nomElemento) {
		this.nomElemento = nomElemento;
	}

	public Integer getCantActual() {
		return cantActual;
	}

	public void setCantActual(Integer cantActual) {
		this.cantActual = cantActual;
	}


	
	
}
