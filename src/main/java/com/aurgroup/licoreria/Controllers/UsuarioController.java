package com.aurgroup.licoreria.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Repositories.UsuarioRepository;

/**
 * UsuarioController
 */

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
	@Autowired
	private UsuarioRepository accountRepository;

	@GetMapping
	public List<Usuario> getAllUsuarios(){
		return accountRepository.findAll();
	}	
	
}
