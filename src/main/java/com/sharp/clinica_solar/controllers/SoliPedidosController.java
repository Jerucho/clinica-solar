package com.sharp.clinica_solar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.sharp.clinica_solar.models.SoliCompra;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.services.SoliPedidosService;


@Controller
@RequestMapping("/jefeCompras")
public class SoliPedidosController {

    private final SoliPedidosService soliPedidosServices;

    public SoliPedidosController(SoliPedidosService soliPedidosServices) {
        this.soliPedidosServices = soliPedidosServices;
    }

    
    @GetMapping("/pedidos")
    public String mostrarSolicitudes(Model model, @SessionAttribute("usuario")Usuario usuario){
        List<SoliCompra> solicitudes = soliPedidosServices.obtenerTodasLasSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("usuario", usuario);	
        return "jefecompras/pedidos";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Long id, @SessionAttribute("usuario") Usuario usuario) {
        soliPedidosServices.eliminarSolicitudPorIdYUsuario(id, usuario);
        return "redirect:/jefeCompras/pedidos";
    }

    @GetMapping("/solicitud/modal/{id}")
    public String verDetalleModal(@PathVariable("id") Long id, Model model) {
        Optional<SoliCompra> opt = soliPedidosServices.obtenerSolicitudPorId(id);
        if (opt.isPresent()) {
            model.addAttribute("solicitud", opt.get());
            return "jefecompras/detallesolicitud :: modalContent";
        }
        return "fragments/error :: mensaje";
    }
}
