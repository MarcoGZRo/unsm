package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Venta;
import com.aurgroup.licoreria.Services.VentaService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VentaController
 * @author M4riotaku
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }

    @PostMapping
    public Venta createVenta(Venta venta) {
        return ventaService.createVenta(venta);
    }

    @PutMapping("/{id}")
    public Venta updateVenta(@PathVariable Long id, Venta venta) {
        return ventaService.updateVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    public Venta anularVenta(@PathVariable Long id) {
        return ventaService.anularVenta(id);
    }
}
