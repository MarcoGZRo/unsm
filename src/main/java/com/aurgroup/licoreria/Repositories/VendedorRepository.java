package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Vendedor;

/**
 * VendedorRepository
 */
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{

	
}
