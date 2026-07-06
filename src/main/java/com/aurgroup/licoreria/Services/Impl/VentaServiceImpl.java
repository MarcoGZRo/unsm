package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.MovimientoInventario;
import com.aurgroup.licoreria.Models.SucursalProducto;
import com.aurgroup.licoreria.Models.Venta;
import com.aurgroup.licoreria.Models.VentaDetalle;
import com.aurgroup.licoreria.Repositories.MovimientoInventarioRepository;
import com.aurgroup.licoreria.Repositories.SucursalProductoRepository;
import com.aurgroup.licoreria.Repositories.VentaDetalleRepository;
import com.aurgroup.licoreria.Repositories.VentaRepository;
import com.aurgroup.licoreria.Services.VentaService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaDetalleRepository ventaDetalleRepository;
    private final SucursalProductoRepository sucursalProductoRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public VentaServiceImpl(
        VentaRepository ventaRepository,
        VentaDetalleRepository ventaDetalleRepository,
        SucursalProductoRepository sucursalProductoRepository,
        MovimientoInventarioRepository movimientoInventarioRepository
    ) {
        this.ventaRepository = ventaRepository;
        this.ventaDetalleRepository = ventaDetalleRepository;
        this.sucursalProductoRepository = sucursalProductoRepository;
        this.movimientoInventarioRepository = movimientoInventarioRepository;
    }

    @Override
    @Transactional
    public Venta createVenta(Venta venta) {
        validarVenta(venta);

        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }

        if (venta.getEstado() == null || venta.getEstado().isBlank()) {
            venta.setEstado("Completado");
        }

        if (venta.getOrigen() == null || venta.getOrigen().isBlank()) {
            venta.setOrigen("Presencial");
        }

        BigDecimal totalVenta = BigDecimal.ZERO;

        for (VentaDetalle detalle : venta.getDetalles()) {
            validarDetalle(detalle);

            Long idSucursal = venta.getSucursal().getIdSucursal();
            Integer idProducto = detalle.getProducto().getIdProducto();

            SucursalProducto stockSucursal = sucursalProductoRepository
                .findBySucursal_IdSucursalAndProducto_IdProducto(
                    idSucursal,
                    idProducto
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "El producto con ID " +
                            idProducto +
                            " no existe en la sucursal con ID " +
                            idSucursal
                    )
                );

            if (stockSucursal.getStock() < detalle.getCantidad()) {
                throw new RuntimeException(
                    "Stock insuficiente para el producto: " +
                        detalle.getProducto().getNombre()
                );
            }

            if (detalle.getPrecioUnitario() == null) {
                detalle.setPrecioUnitario(
                    detalle.getProducto().getPrecioVenta()
                );
            }

            BigDecimal subtotal = detalle
                .getPrecioUnitario()
                .multiply(BigDecimal.valueOf(detalle.getCantidad()));

            detalle.setSubtotal(subtotal);

            totalVenta = totalVenta.add(subtotal);

            stockSucursal.setStock(
                stockSucursal.getStock() - detalle.getCantidad()
            );
            sucursalProductoRepository.save(stockSucursal);
        }

        venta.setTotal(totalVenta);

        Venta ventaGuardada = ventaRepository.save(venta);

        for (VentaDetalle detalle : venta.getDetalles()) {
            detalle.setVenta(ventaGuardada);

            ventaDetalleRepository.save(detalle);

            MovimientoInventario movimiento = new MovimientoInventario();

            movimiento.setSucursal(ventaGuardada.getSucursal());
            movimiento.setProducto(detalle.getProducto());
            movimiento.setUsuario(ventaGuardada.getUsuario());
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setTipo("Salida");
            movimiento.setCantidad(detalle.getCantidad());
            movimiento.setMotivo(
                "Venta " +
                    ventaGuardada.getSerie() +
                    "-" +
                    ventaGuardada.getNumeroSerie()
            );

            movimientoInventarioRepository.save(movimiento);
        }

        return ventaGuardada;
    }

    @Override
    @Transactional(readOnly = true)
    public Venta getVentaById(Long id) {
        return ventaRepository
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException("Venta no encontrada con ID: " + id)
            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getVentasBySucursal(Long idSucursal) {
        return ventaRepository.findBySucursal_IdSucursal(idSucursal);
    }

    @Override
    @Transactional
    public Venta updateVenta(Long id, Venta venta) {
        Venta ventaExistente = getVentaById(id);

        ventaExistente.setSucursal(venta.getSucursal());
        ventaExistente.setCliente(venta.getCliente());
        ventaExistente.setUsuario(venta.getUsuario());
        ventaExistente.setMetodoPago(venta.getMetodoPago());
        ventaExistente.setTipoComprobante(venta.getTipoComprobante());
        ventaExistente.setSerie(venta.getSerie());
        ventaExistente.setNumeroSerie(venta.getNumeroSerie());
        ventaExistente.setFecha(venta.getFecha());
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setEstado(venta.getEstado());
        ventaExistente.setOrigen(venta.getOrigen());

        return ventaRepository.save(ventaExistente);
    }

    @Override
    @Transactional
    public Venta anularVenta(Long id) {
        Venta venta = getVentaById(id);

        venta.setEstado("Anulado");

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id) {
        Venta venta = getVentaById(id);

        ventaRepository.delete(venta);
    }

    private void validarVenta(Venta venta) {
        if (venta == null) {
            throw new RuntimeException("La venta no puede ser nula.");
        }

        if (
            venta.getSucursal() == null ||
            venta.getSucursal().getIdSucursal() == null
        ) {
            throw new RuntimeException("La venta debe tener una sucursal.");
        }

        if (venta.getUsuario() == null || venta.getUsuario().getId() == null) {
            throw new RuntimeException("La venta debe tener un usuario.");
        }

        if (
            venta.getMetodoPago() == null ||
            venta.getMetodoPago().getIdMetodoPago() == null
        ) {
            throw new RuntimeException(
                "La venta debe tener un método de pago."
            );
        }

        if (
            venta.getTipoComprobante() == null ||
            venta.getTipoComprobante().isBlank()
        ) {
            throw new RuntimeException(
                "La venta debe tener tipo de comprobante."
            );
        }

        if (venta.getSerie() == null || venta.getSerie().isBlank()) {
            throw new RuntimeException("La venta debe tener serie.");
        }

        if (
            venta.getNumeroSerie() == null || venta.getNumeroSerie().isBlank()
        ) {
            throw new RuntimeException("La venta debe tener número de serie.");
        }

        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new RuntimeException(
                "La venta debe tener al menos un detalle."
            );
        }
    }

    private void validarDetalle(VentaDetalle detalle) {
        if (
            detalle.getProducto() == null ||
            detalle.getProducto().getIdProducto() == null
        ) {
            throw new RuntimeException("Cada detalle debe tener un producto.");
        }

        if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor que cero.");
        }
    }
}
