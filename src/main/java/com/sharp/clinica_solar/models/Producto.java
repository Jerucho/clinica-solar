package com.sharp.clinica_solar.models;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idElemento")
	private int id;

	@Column(name = "nomElemento", nullable = false, length = 30)
	private String nombre;

	@Column(name = "cantActual")
	private Integer cantidadActual;

	public Producto() {
	}

	public Producto(String nombre, Integer cantidadActual) {
		this.nombre = nombre;
		this.cantidadActual = cantidadActual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Integer cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	@Override
	public String toString() {
		return "Producto{" + "id=" + id + ", nombre='" + nombre + '\'' + ", cantidadActual=" + cantidadActual + '}';
	}
}
