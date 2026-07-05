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
import org.springframework.web.server.ResponseStatusException;

import com.aurgroup.licoreria.Models.Usuario;
import com.aurgroup.licoreria.Repositories.UsuarioRepository;

/**
 * UsuarioController
 */

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> getAllUsuarios(){
		return usuarioRepository.findAll();
	}	
	@PostMapping
	public Usuario postUsuario(@RequestBody Usuario u){
		Optional<Usuario> usuario = usuarioRepository.findByUserName(u.getNombreUsuario());
		if(!usuario.isEmpty()) throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya existe");
		return usuarioRepository.save(u);
	}
	@PutMapping
	public Usuario putUsuario(@RequestBody Usuario u){
		Optional<Usuario> usuario = usuarioRepository.findById(u.getId());
		if(usuario.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usaurio no existe");
		Optional<Usuario> usuarioPorNombre = usuarioRepository.findByUserName(u.getNombreUsuario());
		if(usuarioPorNombre.isPresent() && !usuario.get().getId().equals(u.getId())) 
				throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario no esta disponible");
		
		return usuarioRepository.save(u);	
		
	}
	@DeleteMapping
	public void deleteUsuario(Usuario u) {
		usuarioRepository.deleteById(u.getId());
	}
}
