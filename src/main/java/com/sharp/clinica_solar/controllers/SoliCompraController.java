package com.sharp.clinica_solar.controllers;

import java.util.List;
import java.util.Optional;

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
        List<SoliCompra> solicitudes = soliCompraService.obtenerSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("usuario", usuario);	
        return "medico/listasolicitudes";
    }
    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Long id, @SessionAttribute("usuario") Usuario usuario) {
        soliCompraService.eliminarSolicitudPorIdYUsuario(id, usuario);
        return "redirect:/medico/listaSolicitudes";
    }
    @GetMapping("/solicitud/modal/{id}")
    public String verDetalleModal(@PathVariable("id") Long id, Model model) {
        Optional<SoliCompra> opt = soliCompraService.obtenerSolicitudPorId(id);
        if (opt.isPresent()) {
            model.addAttribute("solicitud", opt.get());
            return "medico/detallesolicitud :: modalContent";
        }
        return "fragments/error :: mensaje";
    }

}
