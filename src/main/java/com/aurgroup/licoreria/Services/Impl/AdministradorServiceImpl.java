package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Administrador;
import com.aurgroup.licoreria.Repositories.AdministradorRepository;
import com.aurgroup.licoreria.Services.AdministradorService;
import org.springframework.stereotype.Service;

/**
 * AdministradorServiceImpl
 */
@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(
        AdministradorRepository administradorRepository
    ) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador updateAdministrador(Administrador a) {
        return administradorRepository.save(a);
    }

    @Override
    public void deleteAdministrador(Integer id) {
        administradorRepository.deleteById(id);
    }

    @Override
    public Administrador getAdministradorById(Integer id) {
        return administradorRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Administrador not found"));
    }

    @Override
    public Administrador createAdministrador(Administrador a) {
        return administradorRepository.save(a);
    }
}
