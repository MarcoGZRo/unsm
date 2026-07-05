package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Carrito;

/**
 * CarritoRepository
 */
@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

	
}
