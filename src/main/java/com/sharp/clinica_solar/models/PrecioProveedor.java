package com.sharp.clinica_solar.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PrecioProveedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrecioProveedor;

    @ManyToOne(optional = false)
    private Proveedor proveedor;

    @ManyToOne(optional = false)
    private Elemento elemento;

    @Column(nullable = false)
    private BigDecimal precioElemento = BigDecimal.ZERO;

    @Column(nullable = false)
    private int cantElemento;
    
    public PrecioProveedor() {
		super();
	}
    
    public PrecioProveedor(Integer idPrecioProveedor, Proveedor proveedor, Elemento elemento, BigDecimal precioElemento, int cantElemento) {
		this.idPrecioProveedor = idPrecioProveedor;
		this.proveedor = proveedor;
		this.elemento = elemento;
		this.precioElemento = precioElemento;
		this.cantElemento = cantElemento;
	}

	public Integer getIdPrecioProveedor() {
		return idPrecioProveedor;
	}

	public void setIdPrecioProveedor(Integer idPrecioProveedor) {
		this.idPrecioProveedor = idPrecioProveedor;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public BigDecimal getPrecioElemento() {
		return precioElemento;
	}

	public void setPrecioElemento(BigDecimal precioElemento) {
		this.precioElemento = precioElemento;
	}

	public int getCantElemento() {
		return cantElemento;
	}

	public void setCantElemento(int cantElemento) {
		this.cantElemento = cantElemento;
	}
		
}
