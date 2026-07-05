package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Venta;

/**
 * VentaRepository
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

	
}
