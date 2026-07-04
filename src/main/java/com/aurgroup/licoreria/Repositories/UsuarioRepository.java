package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurgroup.licoreria.Models.Usuario;

/**
 * UsuarioRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
}
