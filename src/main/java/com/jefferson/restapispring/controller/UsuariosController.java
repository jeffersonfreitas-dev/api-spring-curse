package com.jefferson.restapispring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.restapispring.dto.UsuarioResponse;
import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {
	
	private final UsuarioService service;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioResponse> findAllUsers(){
		return service.findAllUsers();
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteById(@PathVariable String id) {
		return service.deleteById(id);
	}
	
	
	@GetMapping("{nome}")
	public ResponseEntity<List<Usuario>> findByName(@PathVariable String nome){
		List<Usuario> list = service.findAllByName(nome);
		return ResponseEntity.ok(list);
	}

}
