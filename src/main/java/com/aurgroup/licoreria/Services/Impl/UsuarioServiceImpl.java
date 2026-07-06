package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Repositories.UsuarioRepository;
import com.aurgroup.licoreria.Services.UsuarioService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * UsuarioServiceImpl
 * @author M4riotaku
 */

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario createUser(Usuario u) {
        return usuarioRepository.save(u);
    }

    @Override
    public Usuario getUserById(Integer id) {
        return usuarioRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario updateUser(Usuario u) {
        Usuario ue = getUserById(u.getId());
        ue.setNombreUsuario(u.getNombreUsuario());
        ue.setCorreo(u.getCorreo());
        ue.setClave(u.getClave());
        return usuarioRepository.save(ue);
    }

    @Override
    public void deleteUser(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
