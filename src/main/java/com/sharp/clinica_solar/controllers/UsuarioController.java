package com.sharp.clinica_solar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/")
	public String index(Model model,HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			model.addAttribute("usuario", usuario);
			return "home";
		} 
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String inicio(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Usuario usuario, HttpSession session) {
		boolean loginExitoso = usuarioService.iniciarSesion(usuario.getNomUsuario(), usuario.getContraUsuario()); 
		
		if (loginExitoso) {
			Usuario usuarioBD = usuarioService.buscarPorNombre(usuario.getNomUsuario());

			session.setAttribute("usuario", usuarioBD);
			
				switch (usuarioBD.getIdRol()) {
	            case 1:
	                return "redirect:/jefecompras";
	            case 2:
	                return "redirect:/jefeareas";
	            case 3:
	                return "redirect:/medico";
	            default:
	                return "redirect:/";
				}
		}
		return "redirect:/login";
				}
	
		
	@GetMapping("/confirmarLogout")
	public String confirmarLogout(Model model,HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
			model.addAttribute("success", true);
			model.addAttribute("mensaje", "Seguro que quiere finalizar la sesión?");
			
			return "redirect:/";
		} 
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model,HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
			session.removeAttribute("usuario");
			return "redirect:/";
		} 
		return "redirect:/login";
	}
}
