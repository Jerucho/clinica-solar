package com.sharp.clinica_solar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private int idUsuario;

	@Column(name = "idRol")
	private int idRol;
	
	@Column(name = "nomUsuario")
	private String nomUsuario;
	
	@Column(name = "correoUsuario")
	private String correoUsuario;
	
	@Column(name = "contraUsuario")
	private String contraUsuario;

	
	public Usuario() {
		super();
	}
	
	public Usuario(int idUsuario, int idRol, String nomUsuario, String correoUsuario, String contraUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.idRol = idRol;
		this.nomUsuario = nomUsuario;
		this.correoUsuario = correoUsuario;
		this.contraUsuario = contraUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getContraUsuario() {
		return contraUsuario;
	}

	public void setContraUsuario(String contraUsuario) {
		this.contraUsuario = contraUsuario;
	}
	
	
}
