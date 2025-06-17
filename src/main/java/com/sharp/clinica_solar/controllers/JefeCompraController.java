package com.sharp.clinica_solar.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharp.clinica_solar.models.Usuario;

import com.sharp.clinica_solar.services.ElementoService;

import jakarta.servlet.http.HttpSession;
import com.sharp.clinica_solar.models.Elemento;

@Controller
@RequestMapping("/jefecompras")
public class JefeCompraController {

	private final ElementoService _elementoService;

	public JefeCompraController(ElementoService elementoService) {
		this._elementoService = elementoService;
	}

	@GetMapping()
	public String home(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		return "JefeCompras/homeCompras";
	}

	@GetMapping("/inventario")
	public String inventario(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getIdRol() != 1) {
			return "redirect:/login";
		}

		List<Elemento> elementos = _elementoService.encontrarTodos();

		model.addAttribute("inventario", elementos);

		model.addAttribute("usuario", usuario);

		return "Reutilizables/inventario";
	}

	@GetMapping("/pedidos")
	public String pedidos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null || usuario.getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);

		return "JefeCompras/listaCompras";
	}

	@GetMapping("/compra")
	public String compra(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);

		return "JefeCompras/comprarProductos";
	}

	@GetMapping("/proveedores")
	public String proveedores(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);

		return "JefeCompras/listaProveedores";
	}
}
