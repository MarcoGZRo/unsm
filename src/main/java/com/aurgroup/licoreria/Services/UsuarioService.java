package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Usuario;
import java.util.List;

/**
 * UserService
 * @author M4riotaku
 */
public interface UsuarioService {
    Usuario createUser(Usuario u);
    Usuario getUserById(Integer id);
    List<Usuario> getAllUsers();
    Usuario updateUser(Usuario u);
    void deleteUser(Integer id);
}
