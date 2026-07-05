package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Venta;
import com.aurgroup.licoreria.Repositories.VentaRepository;
import com.aurgroup.licoreria.Services.VentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional
    public Venta createVenta(Venta venta) {
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }

        if (venta.getTotal() == null) {
            venta.setTotal(BigDecimal.ZERO);
        }

        if (venta.getEstado() == null || venta.getEstado().isBlank()) {
            venta.setEstado("pendiente");
        }

        if (venta.getOrigen() == null || venta.getOrigen().isBlank()) {
            venta.setOrigen("presencial");
        }

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta getVentaById(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional
    public Venta updateVenta(Long id, Venta venta) {
        Venta ventaExistente = getVentaById(id);

        ventaExistente.setNumeroSerie(venta.getNumeroSerie());
        ventaExistente.setOrigen(venta.getOrigen());
        ventaExistente.setCliente(venta.getCliente());
        ventaExistente.setVendedor(venta.getVendedor());
        ventaExistente.setCaja(venta.getCaja());
        ventaExistente.setMetodoPago(venta.getMetodoPago());
        ventaExistente.setFecha(venta.getFecha());
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setEstado(venta.getEstado());

        return ventaRepository.save(ventaExistente);
    }

    @Override
    @Transactional
    public Venta anularVenta(Long id) {
        Venta venta = getVentaById(id);

        venta.setEstado("anulado");

        return ventaRepository.save(venta);
    }

}