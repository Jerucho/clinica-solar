package com.sharp.clinica_solar.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sharp.clinica_solar.DTOs.SolicitudInsumoDTO;
import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.SoliCompra;
import com.sharp.clinica_solar.models.SoliElemento;
import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.repositories.ElementoRepository;
import com.sharp.clinica_solar.repositories.SoliCompraRepository;
import com.sharp.clinica_solar.repositories.SoliElementoRepository;

@Service
public class SoliCompraService {

	private final SoliCompraRepository soliCompraRepository;
	private final ElementoRepository elementoRepository;
	private final SoliElementoRepository soliElementoRepository;
	
	public SoliCompraService(SoliCompraRepository soliCompraRepository, ElementoRepository elementoRepository, SoliElementoRepository soliElementoRepository) {
		this.soliCompraRepository = soliCompraRepository;
		this.elementoRepository = elementoRepository;
		this.soliElementoRepository = soliElementoRepository;
	}

	public void guardarSolicitudDesdeDTO(List<SolicitudInsumoDTO> insumos, Usuario usuario) {
		SoliCompra solicitud = new SoliCompra();
		solicitud.setFechaCreacion(LocalDateTime.now());
		solicitud.setUsuario(usuario);

		List<SoliElemento> detalle = new ArrayList<>();

		for (SolicitudInsumoDTO dto : insumos) {
		    Elemento elemento = elementoRepository.findById(dto.getIdElemento())
		        .orElseThrow(() -> new RuntimeException("Elemento no encontrado"));

		    SoliElemento sElemento = new SoliElemento();
		    sElemento.setElemento(elemento);
		    sElemento.setCantSolicitada(dto.getCantidad());
		    sElemento.setSolicitud(solicitud);

		    detalle.add(sElemento);
		}

		solicitud.setElementos(detalle); 

		soliCompraRepository.save(solicitud);
	}
	public List<SoliCompra> obtenerSolicitudesPorUsuario(Usuario usuario) {
	    return soliCompraRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);
	}
	
	public List<SoliCompra> obtenerSolicitudes() {
	    return soliCompraRepository.findByStatusSolicitud("P");
	}
	
	public void eliminarSolicitudPorIdYUsuario(Long id, Usuario usuario) {
	    Optional<SoliCompra> solicitud = soliCompraRepository.findById(id);
	    if (solicitud.isPresent() && solicitud.get().getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
	        soliCompraRepository.delete(solicitud.get());
	    }
	}
	public Optional<SoliCompra> obtenerSolicitudPorId(Long id) {
	    return soliCompraRepository.findById(id);
	}
	public void guardarSoliElemento(SoliElemento soliElemento) {
	    soliElementoRepository.save(soliElemento);
	}
	
	public void guardarSoliCompra(SoliCompra soliCompra) {
	    soliCompraRepository.save(soliCompra);
	}

}
