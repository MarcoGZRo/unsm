package com.aurgroup.licoreria.Services;

import java.util.List;

import com.aurgroup.licoreria.Models.Usuario;
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
