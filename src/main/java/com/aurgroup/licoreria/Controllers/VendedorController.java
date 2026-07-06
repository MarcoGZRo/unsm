package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Vendedor;
import com.aurgroup.licoreria.Services.VendedorService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VendedorController
 */
@RestController
@RequestMapping("/api/user/vendedor")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.getAllVendedores();
    }

    @GetMapping("/{id}")
    public Vendedor getVendedorById(@PathVariable Integer id) {
        return vendedorService.getVendedorById(id);
    }

    @PostMapping
    public Vendedor createVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.createVendedor(vendedor);
    }

    @PutMapping("/{id}")
    public Vendedor updateVendedor(
        @PathVariable Integer id,
        @RequestBody Vendedor vendedor
    ) {
        return vendedorService.updateVendedor(id, vendedor);
    }

    @PatchMapping("/{id}")
    public Vendedor inhabilitar(@PathVariable Integer id) {
        return vendedorService.desactivarVendedor(id);
    }
}
