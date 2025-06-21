package com.sharp.clinica_solar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedor;

	@Column(length = 30, nullable = false)
	private String nomProveedor;
	
	public Proveedor() {
		super();
	}
	
	public Proveedor(Integer idProveedor, String nomProveedor) {
		super();
		this.idProveedor = idProveedor;
		this.nomProveedor = nomProveedor;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNomProveedor() {
		return nomProveedor;
	}

	public void setNomProveedor(String nomProveedor) {
		this.nomProveedor = nomProveedor;
	}
	
	
}
