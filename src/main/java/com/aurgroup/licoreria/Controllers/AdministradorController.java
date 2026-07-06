package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Administrador;
import com.aurgroup.licoreria.Services.AdministradorService;
import com.aurgroup.licoreria.Services.ProductoService;
import com.aurgroup.licoreria.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdministradorController
 */
@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    @Autowired
    public AdministradorController(
        AdministradorService administradorService,
        UsuarioService usuarioService,
        ProductoService productoService
    ) {
        this.administradorService = administradorService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    @GetMapping
    public Administrador getAdministradorById(Integer id) {
        return administradorService.getAdministradorById(id);
    }

    @PostMapping
    public Administrador createAdministrador(
        @RequestBody Administrador administrador
    ) {
        return administradorService.createAdministrador(administrador);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrador(@PathVariable Integer id) {
        administradorService.deleteAdministrador(id);
    }

    @PutMapping("/{id}")
    public Administrador updateAdministrador(
        @PathVariable Integer id,
        @RequestBody Administrador administrador
    ) {
        administrador.setIdAdministrador(id);
        return administradorService.updateAdministrador(administrador);
    }

    @DeleteMapping("/productos/{productoId}")
    public void deleteProductoFromAdministrador(
        @PathVariable Integer productoId
    ) {
        productoService.deleteById(productoId);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    public void deleteUsuarioFromAdministrador(
        @PathVariable Integer usuarioId
    ) {
        usuarioService.deleteUser(usuarioId);
    }
}
