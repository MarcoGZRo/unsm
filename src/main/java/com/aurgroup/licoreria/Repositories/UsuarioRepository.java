package com.aurgroup.licoreria.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurgroup.licoreria.Models.Usuario;

/**
 * UsuarioRepository
 * @author M4riotaku
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUserName(String userName);
	Optional<Usuario> findByEmail(String correo);
	boolean existUserName(String userName);
}
