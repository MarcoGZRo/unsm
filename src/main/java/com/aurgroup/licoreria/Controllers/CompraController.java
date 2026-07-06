package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Compra;
import com.aurgroup.licoreria.Services.CompraService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public Compra createCompra(@RequestBody Compra compra) {
        return compraService.createCompra(compra);
    }

    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable Long id) {
        return compraService.getCompraById(id);
    }

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraService.getAllCompras();
    }

    @GetMapping("/sucursal/{idSucursal}")
    public List<Compra> getComprasBySucursal(@PathVariable Long idSucursal) {
        return compraService.getComprasBySucursal(idSucursal);
    }

    @GetMapping("/proveedor/{idProveedor}")
    public List<Compra> getComprasByProveedor(
        @PathVariable Integer idProveedor
    ) {
        return compraService.getComprasByProveedor(idProveedor);
    }

    @GetMapping("/administrador/{idAdministrador}")
    public List<Compra> getComprasByAdministrador(
        @PathVariable Integer idAdministrador
    ) {
        return compraService.getComprasByAdministrador(idAdministrador);
    }

    @GetMapping("/estado/{estado}")
    public List<Compra> getComprasByEstado(@PathVariable String estado) {
        return compraService.getComprasByEstado(estado);
    }

    @PutMapping("/{id}")
    public Compra updateCompra(
        @PathVariable Long id,
        @RequestBody Compra compra
    ) {
        return compraService.updateCompra(id, compra);
    }

    @PutMapping("/{id}/anular")
    public Compra anularCompra(@PathVariable Long id) {
        return compraService.anularCompra(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompra(@PathVariable Long id) {
        compraService.deleteCompra(id);
    }
}
