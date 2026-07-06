package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Administrador;

/**
 * AdministradorService
 * @author M4riotaku
 */
public interface AdministradorService {
    Administrador updateAdministrador(Administrador a);
    void deleteAdministrador(Integer id);
    Administrador getAdministradorById(Integer id);
    Administrador createAdministrador(Administrador a);
}
