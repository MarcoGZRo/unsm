package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Compra;

/**
 * CompraRepository
 */
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	
}
