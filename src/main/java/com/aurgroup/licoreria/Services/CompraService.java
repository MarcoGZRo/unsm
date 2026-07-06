package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Compra;
import java.util.List;

/**
 * CompraService
 * @author M4riotaku
 */

public interface CompraService {
    Compra createCompra(Compra compra);

    Compra getCompraById(Long id);

    List<Compra> getAllCompras();

    List<Compra> getComprasBySucursal(Long idSucursal);

    List<Compra> getComprasByProveedor(Integer idProveedor);

    List<Compra> getComprasByAdministrador(Integer idAdministrador);

    List<Compra> getComprasByEstado(String estado);

    Compra updateCompra(Long id, Compra compra);

    Compra anularCompra(Long id);

    void deleteCompra(Long id);
}
