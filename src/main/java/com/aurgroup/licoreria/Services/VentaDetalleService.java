package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.VentaDetalle;
import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
public interface VentaDetalleService {

    VentaDetalle createVentaDetalle(VentaDetalle ventaDetalle);

    VentaDetalle getVentaDetalleById(Long id);

    List<VentaDetalle> getAllVentaDetalles();

    VentaDetalle updateVentaDetalle(Long id, VentaDetalle ventaDetalle);

    void deleteVentaDetalle(Long id);
}
