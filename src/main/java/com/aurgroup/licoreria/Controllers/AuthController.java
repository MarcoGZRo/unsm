package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Services.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 * @author M4riotaku
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService as) {
        this.authService = as;
    }

    @GetMapping
    public Usuario login(Usuario u) {
        return authService.login(u.getNombreUsuario(), u.getClave());
    }

    @PostMapping
    public void register(Usuario u) {
        authService.register(u);
    }
}
