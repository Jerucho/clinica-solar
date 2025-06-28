package com.sharp.clinica_solar.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.services.ElementoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/jefeareas")
public class JefeAreaController {
	private final ElementoService _elementoService;

	public JefeAreaController(ElementoService elementoService) {
		this._elementoService = elementoService;
	}

	@GetMapping()
	public String home(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 2) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		return "JefeAreas/homeArea";
	}

	@GetMapping("/inventario")
	public String inventario(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 2) {
			return "redirect:/login";
		}

		List<Elemento> elementos = _elementoService.encontrarTodos();

		model.addAttribute("inventario", elementos);

		model.addAttribute("usuario", usuario);

		return "Reutilizables/inventario";
	}
	
	@GetMapping("/productos")
	public String productos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 2) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("elemento", new Elemento());
		return "JefeAreas/productos";
	}
}
