package com.aurgroup.licoreria.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Services.UsuarioService;

/**
 * UsuarioController
 */

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
	@Autowired
	private final UsuarioService usuarioService;
	public UsuarioController(UsuarioService us) {
		this.usuarioService = us;
	}

	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsers();
	}	
	
	@PostMapping
	public Usuario postUsuario(@RequestBody Usuario u) {
		return usuarioService.createUser(u);	
		// Optional<Usuario> usuario = usuarioRepository.findByUserName(u.getNombreUsuario());
		// if(!usuario.isEmpty()) throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya existe");
	}
	
	@PutMapping
	public Usuario putUsuario(@RequestBody Usuario u){
		return usuarioService.updateUser(u);
		// Optional<Usuario> usuario = usuarioRepository.findById(u.getId());
		// if(usuario.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usaurio no existe");
		// return usuarioRepository.save(u);	
	}
	@DeleteMapping
	public void deleteUsuario(Usuario u) {
		usuarioService.deleteUser(u.getId());
	}
}
