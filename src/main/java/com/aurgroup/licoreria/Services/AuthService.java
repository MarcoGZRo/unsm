package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Usuario;
/**
 * AuthService
 * @author M4riotaku
 */
public interface AuthService {

	void login(String userName, String password);
	void register(Usuario u);
}
