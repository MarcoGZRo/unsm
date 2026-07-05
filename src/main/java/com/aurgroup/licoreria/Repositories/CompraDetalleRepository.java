package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.CompraDetalle;

/**
 * CompraDetalleRepository
 */
@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long>{

	
}
