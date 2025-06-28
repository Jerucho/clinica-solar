package com.sharp.clinica_solar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.Producto;
import com.sharp.clinica_solar.repositories.ProductoRepository;
import com.sharp.clinica_solar.services.ElementoService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/elementos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepo;
    
    
    
    @Autowired
    private ElementoService elementoService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "Medico/crearLista";
    }
    
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable int id, Model model) {
        Optional<Producto> producto = productoRepo.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "jefeareas/productos";
        } else {
            return "redirect:/jefeareas/productos";
        }
    }
    
    @PostMapping("/guardar")
  public void guardarProducto(@ModelAttribute Elemento elemento, HttpServletResponse response) throws IOException {
		elementoService.guardarElemento(elemento);
		response.sendRedirect("/jefeareas/productos");
	}


    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id ) {
        elementoService.eliminarElemento(id);
        return "redirect:/jefeareas/productos";
    }
    
    @GetMapping("/buscar")
    public List<Elemento> buscar(@RequestParam String query) {
        return elementoService.encontrarCoincidenciasPorNombre(query);
    }
}

