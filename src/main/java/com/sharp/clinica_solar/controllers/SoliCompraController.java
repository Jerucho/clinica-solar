package com.sharp.clinica_solar.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sharp.clinica_solar.models.SoliCompra;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.services.SoliCompraService;

@Controller
@RequestMapping("/medico")
public class SoliCompraController {

    private final SoliCompraService soliCompraService;

    public SoliCompraController(SoliCompraService soliCompraService) {
        this.soliCompraService = soliCompraService;
    }

    @GetMapping("/listaSolicitudes")
    public String mostrarSolicitudes(Model model, @SessionAttribute("usuario") Usuario usuario) {
        List<SoliCompra> solicitudes = soliCompraService.obtenerSolicitudesPorUsuario(usuario);
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("usuario", usuario);	
        return "medico/listasolicitudes";
    }
    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Long id, @SessionAttribute("usuario") Usuario usuario) {
        soliCompraService.eliminarSolicitudPorIdYUsuario(id, usuario);
        return "redirect:/medico/listaSolicitudes";
    }
}
