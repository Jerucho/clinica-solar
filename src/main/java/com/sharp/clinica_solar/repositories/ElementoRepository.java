package com.sharp.clinica_solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharp.clinica_solar.models.Elemento;

@Repository
public interface ElementoRepository extends JpaRepository<Elemento, Long> {
		
	List<Elemento> findByNomElementoContainingIgnoreCase(String query);
}
