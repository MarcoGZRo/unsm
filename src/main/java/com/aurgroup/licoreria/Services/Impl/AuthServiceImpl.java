package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Repositories.UsuarioRepository;
import com.aurgroup.licoreria.Services.AuthService;
import org.springframework.stereotype.Service;

/**
 * AuthServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario login(String userName, String password) {
        return usuarioRepository
            .findByUserName(userName)
            .filter(u -> u.getClave().equals(password))
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    @Override
    public void register(Usuario u) {
        usuarioRepository.save(u);
    }
}
