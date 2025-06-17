package com.sharp.clinica_solar.services;

import java.util.List;

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
	
}
