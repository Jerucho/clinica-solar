package com.sharp.clinica_solar.controllers;

import com.sharp.clinica_solar.DTOs.ElementoProveedorDTO;
import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.PrecioProveedor;
import com.sharp.clinica_solar.models.Proveedor;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.repositories.ProvedorPrecioRepository;
import com.sharp.clinica_solar.services.ElementoService;
import com.sharp.clinica_solar.services.ProveedorPrecioService;
import com.sharp.clinica_solar.services.ProveedorService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@Autowired
	private ProveedorPrecioService proveedorPrecioService;

	@Autowired
	private ElementoService elementoService;

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

	@GetMapping("/elementos/{id}")
	public String productosProveedor(@PathVariable("id") Integer id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		List<PrecioProveedor> precios = proveedorPrecioService.encontrarPorProveedor(id);
		Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
		List<Elemento> elementos = elementoService.encontrarTodos();

		if (usuario == null || usuario.getRol().getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		if (proveedor == null) {
			return "redirect:/jefecompras/proveedores";
		}

		model.addAttribute("elementoDTO", new ElementoProveedorDTO());
		model.addAttribute("elementos", elementos);
		model.addAttribute("proveedor", proveedor);
		model.addAttribute("precios", precios);

		model.addAttribute("listaProveedores", proveedorService.listarProveedor());
		return "Proveedor/index";
	}

	@PostMapping("/elemento/agregar")
	public String guardarElementoAProveedor(@ModelAttribute ElementoProveedorDTO dto) {
		proveedorPrecioService.guardarPrecioElemento(dto);

		return "redirect:/proveedores/elementos/" + dto.getProveedorId();
	}

	@PostMapping("/editar/{id}")
	public String editarProveedor(@PathVariable("id") Integer id, @ModelAttribute Proveedor proveedorActualizado,
			Model model) {
		Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
		if (proveedor != null) {
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
