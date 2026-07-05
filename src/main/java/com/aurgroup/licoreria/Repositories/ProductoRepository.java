package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aurgroup.licoreria.Models.Producto;

/**
 * ProductoRepository
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

	
}
