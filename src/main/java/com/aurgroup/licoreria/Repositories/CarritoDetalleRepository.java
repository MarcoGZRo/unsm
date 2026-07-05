package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.CarritoDetalle;

/**
 * CarritoDetalleRepository
 */
@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long>{

	
}
