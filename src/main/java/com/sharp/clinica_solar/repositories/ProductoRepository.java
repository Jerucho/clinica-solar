package com.sharp.clinica_solar.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sharp.clinica_solar.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
