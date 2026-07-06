package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Services.AuthService;
import org.springframework.web.bind.annotation.*;

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
    public Usuario login(@RequestParam String nombreUsuario,
                         @RequestParam String clave) {
        return authService.login(nombreUsuario, clave);
    }

    @PostMapping
    public void register(Usuario u) {
        authService.register(u);
    }
}
