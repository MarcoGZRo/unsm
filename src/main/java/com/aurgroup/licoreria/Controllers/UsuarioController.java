package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Services.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsuarioController
 */

@RestController
@RequestMapping("/api/user")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService us) {
        this.usuarioService = us;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsers();
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario u) {
        return usuarioService.createUser(u);
    }

    @PutMapping
    public Usuario putUsuario(@RequestBody Usuario u) {
        return usuarioService.updateUser(u);
    }

    @DeleteMapping
    public void deleteUsuario(Usuario u) {
        usuarioService.deleteUser(u.getId());
    }
}
