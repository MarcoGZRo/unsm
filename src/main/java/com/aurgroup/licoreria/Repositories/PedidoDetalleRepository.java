package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.PedidoDetalle;

/**
 * PedidoDetalleRepository
 */
@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {

	
}
