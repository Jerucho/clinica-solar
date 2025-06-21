package com.sharp.clinica_solar.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sharp.clinica_solar.DTOs.SolicitudInsumoDTO;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.services.SoliCompraService;
import com.sharp.clinica_solar.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicoController {

	private final UsuarioService usuarioService;

	private final SoliCompraService soliCompraService;

	// Constructor
	public MedicoController(UsuarioService usuarioService, SoliCompraService soliCompraService) {
		this.usuarioService = usuarioService;
		this.soliCompraService = soliCompraService;
	}

	@GetMapping("/medico")
	public String inicio(Model model, HttpSession session) {
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			model.addAttribute("usuario", usuario);
			return "Medico/index";
		}
		return "redirect:/login";
	}

	@GetMapping("/medico/solicitarCompra")
	public String solicitarCompra(Model model, HttpSession session) {
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			model.addAttribute("usuario", usuario);
			return "Medico/crearLista";
		}
		return "redirect:/login";
	}

	@PostMapping("/medico/solicitarCompra")
	public String solicitarCompraPost(Model model, HttpSession session, @RequestBody List<SolicitudInsumoDTO> insumos) {
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			model.addAttribute("usuario", usuario);

			soliCompraService.guardarSolicitudDesdeDTO(insumos, usuario);

			return "redirect:/medico/solicitarCompra";
		}
		return "redirect:/login";
	}

}
