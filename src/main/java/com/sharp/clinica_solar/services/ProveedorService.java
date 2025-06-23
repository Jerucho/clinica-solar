package com.sharp.clinica_solar.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sharp.clinica_solar.models.Proveedor;
import com.sharp.clinica_solar.repositories.ProveedorRepository;

@Service
public class ProveedorService {
  private final ProveedorRepository proveedorRepository;

  public ProveedorService(ProveedorRepository proveedorRepository) {
    this.proveedorRepository = proveedorRepository;
  }

  public List<Proveedor> listarProveedor() {
    return proveedorRepository.findAll();
  }
  
  public Proveedor guardarProveedor(Proveedor proveedor) {
    return proveedorRepository.save(proveedor);
  }
  
  public Proveedor buscarProveedorPorId(Integer idProveedor) {
    return proveedorRepository.findById(idProveedor).orElse(null);
  }
  
  public void eliminarProveedor(Integer idProveedor) {
    proveedorRepository.deleteById(idProveedor);
  }
}
