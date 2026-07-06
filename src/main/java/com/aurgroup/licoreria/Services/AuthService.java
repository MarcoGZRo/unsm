package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Usuario;

/**
 * AuthService
 * @author M4riotaku
 */
public interface AuthService {
    Usuario login(String userName, String password);
    void register(Usuario u);
}
