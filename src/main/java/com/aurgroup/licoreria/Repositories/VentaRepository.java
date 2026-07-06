package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.Venta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VentaRepository
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findBySucursal_IdSucursal(Long idSucursal);

    Optional<Venta> findBySerieAndNumeroSerie(String serie, String numeroSerie);

    List<Venta> findByEstado(String estado);
}
