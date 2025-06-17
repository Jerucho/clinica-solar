package com.sharp.clinica_solar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Elemento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idElemento;
	
	private String nomElemento;
	
	private int cantActual;
	
	public Elemento() {
		super();
	}

	public Elemento(Long idElemento, String nomElemento, int cantActual) {
		super();
		this.idElemento = idElemento;
		this.nomElemento = nomElemento;
		this.cantActual = cantActual;
	}

	public Long getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}

	public String getNomElemento() {
		return nomElemento;
	}

	public void setNomElemento(String nomElemento) {
		this.nomElemento = nomElemento;
	}

	public int getCantActual() {
		return cantActual;
	}

	public void setCantActual(int cantActual) {
		this.cantActual = cantActual;
	}
	
	
	
	
}
