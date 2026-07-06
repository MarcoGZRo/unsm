package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Venta;
import java.util.List;

/**
 * AuthService
 * @author MarcoGZRo
 */
public interface VentaService {
    Venta createVenta(Venta venta);

    Venta getVentaById(Long id);

    List<Venta> getAllVentas();

    List<Venta> getVentasBySucursal(Long idSucursal);

    Venta updateVenta(Long id, Venta venta);

    Venta anularVenta(Long id);

    void deleteVenta(Long id);
}
