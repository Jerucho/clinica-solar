package com.sharp.clinica_solar.DTOs;

import java.math.BigDecimal;

public class ElementoProveedorDTO {
    private Long proveedorId;
    private Long elementoId;
    private BigDecimal precioUnitario;
    
    public ElementoProveedorDTO() {
		super();
	}
    
	public ElementoProveedorDTO(Long proveedorId, Long elementoId, BigDecimal precioUnitario) {
		super();
		this.proveedorId = proveedorId;
		this.elementoId = elementoId;
		this.precioUnitario = precioUnitario;
	}
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	public Long getElementoId() {
		return elementoId;
	}
	public void setElementoId(Long elementoId) {
		this.elementoId = elementoId;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
    
    

}
