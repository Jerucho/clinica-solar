package com.sharp.clinica_solar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "idRol")
	private int idRol;
	@Column(name = "descRol")
	private String descRol;
	
	public Rol() {
		super();
	}
	
	public Rol(int idRol, String descRol) {
		super();
		this.idRol = idRol;
		this.descRol = descRol;
	}
	
	//get and set methods
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getDescRol() {
		return descRol;
	}
	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}
	

	
	
}
