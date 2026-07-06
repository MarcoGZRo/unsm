package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.VentaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VentaDetalleRepository
 */
@Repository
public interface VentaDetalleRepository
    extends JpaRepository<VentaDetalle, Long>
{
    List<VentaDetalle> findByVenta_IdVenta(Integer idVenta);
}
