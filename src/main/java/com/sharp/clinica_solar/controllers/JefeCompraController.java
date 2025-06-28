package com.sharp.clinica_solar.controllers;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.repositories.ProvedorPrecioRepository;
import com.sharp.clinica_solar.services.ElementoService;
import com.sharp.clinica_solar.services.ProveedorPrecioService;

import jakarta.servlet.http.HttpSession;

import com.sharp.clinica_solar.models.Compra;
import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.PrecioProveedor;
import com.sharp.clinica_solar.models.Proveedor;
import com.sharp.clinica_solar.models.SoliCompra;
import com.sharp.clinica_solar.models.SoliElemento;
import com.sharp.clinica_solar.services.ProveedorService;
import com.sharp.clinica_solar.services.SoliCompraService;

@Controller
@RequestMapping("/jefecompras")
public class JefeCompraController {

	private final ElementoService _elementoService;
	private final ProveedorService _proveedorService;
	private final ProveedorPrecioService proveedorPrecioService;
	private final SoliCompraService soliCompraService;
	private final ProvedorPrecioRepository precioProveedorRepo;

	public JefeCompraController(ElementoService elementoService, ProveedorService proveedorService,
			SoliCompraService soliCompraService, ProveedorPrecioService proveedorPrecioService,
			ProvedorPrecioRepository precioProveedorRepo) {
		this._elementoService = elementoService;
		this._proveedorService = proveedorService;
		this.soliCompraService = soliCompraService;
		this.proveedorPrecioService = proveedorPrecioService;
		this.precioProveedorRepo = precioProveedorRepo;
	}

	@GetMapping()
	public String home(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		return "JefeCompras/homeCompras";
	}

	@GetMapping("/inventario")
	public String inventario(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 1) {
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
		List<SoliCompra> solicitudes = soliCompraService.obtenerSolicitudes();

		if (usuario == null || usuario.getRol().getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("solicitudes", solicitudes);

		model.addAttribute("usuario", usuario);

		return "JefeCompras/listaCompras";
	}

	@GetMapping("/pedidos/{id}")
	public String completarPedido(Model model, @PathVariable("id") Long id, HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");
	    Optional<SoliCompra> solicitudOpt = soliCompraService.obtenerSolicitudPorId(id);

	    if (usuario == null || usuario.getRol().getIdRol() != 1)
	        return "redirect:/login";
	    if (solicitudOpt.isEmpty())
	        return "redirect:/pedidos";

	    SoliCompra solicitud = solicitudOpt.get();
	    List<SoliElemento> items = solicitud.getElementos();

	    Map<Integer, Map<String, Object>> precioMap = new HashMap<>();
	    BigDecimal totalCompra = BigDecimal.ZERO;

	    for (SoliElemento item : items) {
	        List<PrecioProveedor> precios = precioProveedorRepo
	                .findByElemento_IdElemento(item.getElemento().getIdElemento());

	        PrecioProveedor mejor = precios.stream().min(Comparator.comparing(PrecioProveedor::getPrecioElemento))
	                .orElse(null);

	        if (mejor != null) {
	            Map<String, Object> data = new HashMap<>();
	            data.put("precioElemento", mejor.getPrecioElemento());
	            data.put("proveedores",
	                    precios.stream()
	                            .filter(p -> p.getPrecioElemento().compareTo(mejor.getPrecioElemento()) == 0)
	                            .map(PrecioProveedor::getProveedor)
	                            .collect(Collectors.toList()));

	            precioMap.put(item.getElemento().getIdElemento(), data);

	            // Acumular total
	            BigDecimal subtotal = mejor.getPrecioElemento().multiply(BigDecimal.valueOf(item.getCantSolicitada()));
	            totalCompra = totalCompra.add(subtotal);
	        }
	    }

	    model.addAttribute("usuario", usuario);
	    model.addAttribute("solicitud", solicitud);
	    model.addAttribute("precioMap", precioMap);
	    model.addAttribute("totalCompra", totalCompra);
	    return "JefeCompras/comprarProductos";
	}


	@GetMapping("/proveedores")
	public String proveedores(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null || usuario.getRol().getIdRol() != 1) {
			return "redirect:/login";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("listaProveedores", _proveedorService.listarProveedor());
		return "JefeCompras/listaProveedores";
	}

	@PostMapping("/compras/finalizar")
	public String finalizarCompra(@RequestParam("solicitudId") Long solicitudId,
			@RequestParam Map<String, String> allParams) {

		Optional<SoliCompra> opSolicitud = soliCompraService.obtenerSolicitudPorId(solicitudId);
		if (opSolicitud.isEmpty())
			return "redirect:/pedidos";

		SoliCompra solicitud = opSolicitud.get();

		for (SoliElemento item : solicitud.getElementos()) {
			String key = "proveedor_" + item.getElemento().getIdElemento();
			String proveedorIdStr = allParams.get(key);

			if (proveedorIdStr != null) {
				int proveedorId = Integer.parseInt(proveedorIdStr);

				PrecioProveedor precioProveedor = precioProveedorRepo
						.findByElemento_IdElementoAndProveedor_IdProveedor(item.getElemento().getIdElemento(),
								proveedorId)
						.orElseThrow(() -> new RuntimeException("PrecioProveedor no encontrado"));

				item.setPrecioSeleccionado(precioProveedor);
				soliCompraService.guardarSoliElemento(item);
			}
		}

		solicitud.setStatusSolicitud("C"); // C = Comprado
		soliCompraService.guardarSoliCompra(solicitud);

		return "redirect:/jefecompras/pedidos";
	}

}
