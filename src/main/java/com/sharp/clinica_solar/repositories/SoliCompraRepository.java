package com.sharp.clinica_solar.repositories;
import com.sharp.clinica_solar.models.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharp.clinica_solar.models.SoliCompra;

public interface SoliCompraRepository extends JpaRepository<SoliCompra, Long> {
	List<SoliCompra> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario);
	List<SoliCompra> findByStatusSolicitud(String string);
}
