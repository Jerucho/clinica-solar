package com.sharp.clinica_solar.services;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.clinica_solar.DTOs.ElementoProveedorDTO;
import com.sharp.clinica_solar.models.Elemento;
import com.sharp.clinica_solar.models.PrecioProveedor;
import com.sharp.clinica_solar.models.Proveedor;
import com.sharp.clinica_solar.repositories.ElementoRepository;
import com.sharp.clinica_solar.repositories.ProvedorPrecioRepository;
import com.sharp.clinica_solar.repositories.ProveedorRepository;

@Service
public class ProveedorPrecioService {

    @Autowired
    private ProvedorPrecioRepository precioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ElementoRepository elementoRepository;

	
	public List<PrecioProveedor> encontrarPorProveedor(int proveedorId) {
		return precioRepository.findByProveedor_IdProveedor(proveedorId);
	}
	
	
	public void guardarPrecioElemento(ElementoProveedorDTO dto) {
        Proveedor proveedor = proveedorRepository.findById((int) dto.getProveedorId().longValue())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Elemento elemento = elementoRepository.findById(dto.getElementoId().longValue())
                .orElseThrow(() -> new RuntimeException("Elemento no encontrado"));


        PrecioProveedor precio = new PrecioProveedor();
        precio.setProveedor(proveedor);
        precio.setElemento(elemento);
        precio.setPrecioElemento(dto.getPrecioUnitario());
        precio.setCantElemento(1);

        precioRepository.save(precio);
    }
	
	
	public Map<Integer, List<Proveedor>> obtenerProveedoresConMejorPrecioPorElemento(List<Integer> idsElementos) {
	    Map<Integer, List<Proveedor>> resultado = new HashMap<>();

	    for (int idElemento : idsElementos) {
	        List<PrecioProveedor> precios = precioRepository.findByElemento_IdElemento(idElemento);

	        if (!precios.isEmpty()) {
	            BigDecimal menorPrecio = precios.stream()
	                .map(PrecioProveedor::getPrecioElemento)
	                .min(BigDecimal::compareTo)
	                .orElse(BigDecimal.ZERO);

	            List<Proveedor> mejoresProveedores = precios.stream()
	                .filter(p -> p.getPrecioElemento().compareTo(menorPrecio) == 0)
	                .map(PrecioProveedor::getProveedor)
	                .distinct()
	                .toList();

	            resultado.put(idElemento, mejoresProveedores);
	        }
	    }

	    return resultado;
	}
	
	public Map<Integer, BigDecimal> obtenerMejorPrecioPorElemento(List<Integer> idsElementos) {
	    Map<Integer, BigDecimal> mapa = new HashMap<>();

	    for (Integer idElemento : idsElementos) {
	        List<PrecioProveedor> precios = precioRepository.findByElemento_IdElemento(idElemento);
	        precios.stream()
	            .min(Comparator.comparing(PrecioProveedor::getPrecioElemento))
	            .ifPresent(precio -> mapa.put(idElemento, precio.getPrecioElemento()));
	    }

	    return mapa;
	}


}
