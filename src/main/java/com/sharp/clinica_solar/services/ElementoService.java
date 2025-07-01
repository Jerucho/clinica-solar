package com.sharp.clinica_solar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sharp.clinica_solar.repositories.ElementoRepository;
import com.sharp.clinica_solar.models.Elemento;

@Service
public class ElementoService {

	private final ElementoRepository _elementoRepository;

	public ElementoService(ElementoRepository elementoRepository) {
		this._elementoRepository = elementoRepository;
	}

	public List<Elemento> encontrarTodos() {
		return _elementoRepository.findAll();
	}

	public List<Elemento> encontrarCoincidenciasPorNombre(String query) {
		return _elementoRepository.findByNomElementoContainingIgnoreCase(query);
	}

	public void guardarElemento(Elemento elemento) {
		_elementoRepository.save(elemento);
	}

	public void actualizarStock(int cantidad, int idElemento) {
		Optional<Elemento> elementoOpt = _elementoRepository.findById((long) idElemento);
		if (elementoOpt.isPresent()) {
			Elemento elemento = elementoOpt.get();
			elemento.setCantActual(elemento.getCantActual() + cantidad);
			_elementoRepository.save(elemento);
		} else {
			throw new RuntimeException("Elemento no encontrado");
		}
	}

	public void eliminarElemento(Long idElemento) {
		_elementoRepository.deleteById(idElemento);
	}

	public Optional<Elemento> obtenerPorId(Long id) {
		return _elementoRepository.findById(id);
	}
}
