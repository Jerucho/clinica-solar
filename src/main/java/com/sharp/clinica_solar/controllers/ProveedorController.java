package com.sharp.clinica_solar.controllers;

import com.sharp.clinica_solar.models.Proveedor;
import com.sharp.clinica_solar.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
  
  @Autowired
  private ProveedorService proveedorService;
  
  @PostMapping("/guardar")
  public String guardarProveedor(@ModelAttribute Proveedor proveedor, Model model) {
    proveedorService.guardarProveedor(proveedor);
    return "redirect:/jefecompras/proveedores";
  }
  
  @GetMapping("/eliminar/{id}")
  public String eliminarProveedor(@PathVariable("id") Integer id, Model model) {
    proveedorService.eliminarProveedor(id);
    model.addAttribute("proveedor", new Proveedor());
    model.addAttribute("listaProveedores", proveedorService.listarProveedor());
    return "redirect:/jefecompras/proveedores";
  }
  
  @PostMapping("/editar/{id}")
  public String editarProveedor(@PathVariable("id") Integer id, @ModelAttribute Proveedor proveedorActualizado, Model model) {
    Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
    if(proveedor != null) {
      proveedor.setNomProveedor(proveedorActualizado.getNomProveedor());
      proveedor.setTelefono(proveedorActualizado.getTelefono());
      proveedor.setCorreo(proveedorActualizado.getCorreo());
      proveedorService.guardarProveedor(proveedor);
    }
    model.addAttribute("proveedor", new Proveedor());
    model.addAttribute("listaProveedores", proveedorService.listarProveedor());
    return "redirect:/jefecompras/proveedores";
  }
}
