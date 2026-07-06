package com.aurgroup.licoreria.Repositories;

import com.aurgroup.licoreria.Models.CompraDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraDetalleRepository
    extends JpaRepository<CompraDetalle, Integer>
{
    List<CompraDetalle> findByCompra_IdCompra(Integer idCompra);

    List<CompraDetalle> findByProducto_IdProducto(Integer idProducto);
}
