package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.MetodoPago;

/**
 * MetodoPagoRepository
 */
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

	
}
