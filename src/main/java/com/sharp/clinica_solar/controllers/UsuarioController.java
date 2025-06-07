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
	
	@GetMapping("/jefecompras")
	public String JefeCompras(HttpSession session) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/login";
	    }
	    return "/JefeCompras/homeCompras";
	}
	
	@GetMapping("/jefeareas")
	public String JefeAreas(HttpSession session) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/login";
	    }
	    return "/jefeAreas";
	}

	
	
	@PostMapping("/login")
	public String login(@ModelAttribute Usuario usuario, HttpSession session) {
		boolean loginExitoso = usuarioService.iniciarSesion(usuario.getNomUsuario(), usuario.getContraUsuario()); 
		
		if (loginExitoso) {
			Usuario usuarioBD = usuarioService.buscarPorNombre(usuario.getNomUsuario());
			session.setAttribute("usuario", usuarioBD);
			
			String rolDesc = usuarioBD.getIdRol() == 1 ? "JefeCompras" :
				usuarioBD.getIdRol() == 2 ? "JefeAreas" :
				usuarioBD.getIdRol() == 3 ? "Medico" : "Usuario"; // Asignar rol por id
			
			switch (rolDesc) {
            case "JefeCompras":
                return "redirect:/jefecompras";
            case "JefeAreas":
                return "redirect:/jefeareas";
            case "Medico":
                return "redirect:/medico";
            default:
                return "redirect:/";
        }
    }
    return "redirect:/login";
	}
}
