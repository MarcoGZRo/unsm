package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.Compra;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
        
        
            List<Compra> findBySucursal_IdSucursal(Long idSucursal);

    List<Compra> findByProveedor_IdProveedor(Integer idProveedor);

    List<Compra> findByAdministrador_IdAdministrador(Integer idAdministrador);

    List<Compra> findByEstado(String estado);

    Optional<Compra> findBySucursal_IdSucursalAndSerieAndNumeroSerie(
        Integer idSucursal,
        String serie,
        String numeroSerie
    );
}
