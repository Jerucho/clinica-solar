package com.sharp.clinica_solar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sharp.clinica_solar.models.Producto;
import com.sharp.clinica_solar.repositories.ProductoRepository;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepo;

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
            return "productos/formulario";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepo.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoRepo.deleteById(id);
        return "redirect:/productos";
    }
}

