package com.sharp.clinica_solar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharp.clinica_solar.models.PrecioProveedor;

@Repository
public interface ProvedorPrecioRepository extends JpaRepository<PrecioProveedor, Integer>{
	List<PrecioProveedor> findByProveedor_IdProveedor(Integer idProveedor);

	List<PrecioProveedor> findByElemento_IdElemento(int idElemento);

	 Optional<PrecioProveedor> findByElemento_IdElementoAndProveedor_IdProveedor(Integer elementoId, Integer proveedorId);
}
