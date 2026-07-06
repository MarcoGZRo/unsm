package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.SucursalProducto;
import com.aurgroup.licoreria.Models.SucursalProductoId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalProductoRepository
    extends JpaRepository<SucursalProducto, SucursalProductoId>
{
    Optional<SucursalProducto> findBySucursal_IdSucursalAndProducto_IdProducto(
        Long idSucursal,
        Integer idProducto
    );
}
