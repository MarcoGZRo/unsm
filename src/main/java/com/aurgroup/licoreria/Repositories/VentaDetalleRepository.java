package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.VentaDetalle;

/**
 * VentaDetalleRepository
 */
@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {

	
}
