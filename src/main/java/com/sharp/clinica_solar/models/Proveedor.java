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
  
  @Column(length = 20, nullable = false)
  private String telefono;
  
  @Column(length = 100, nullable = false)
  private String correo;
  
  public Proveedor() {
    super();
  }

  public Proveedor(Integer idProveedor, String nomProveedor, String telefono, String correo) {
    super();
    this.idProveedor = idProveedor;
    this.nomProveedor = nomProveedor;
    this.telefono = telefono;
    this.correo = correo;
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

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }
}
