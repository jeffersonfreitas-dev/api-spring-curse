package com.jefferson.restapispring.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.restapispring.dto.UsuarioDto;
import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {
	
	private final UsuarioService service;
	
	@GetMapping("page/{pagina}")
	@ResponseStatus(HttpStatus.OK)
	public Page<Usuario> findAllUsers(@PathVariable Integer pagina){
		return service.findAllUsers(pagina);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteById(@PathVariable String id) {
		return service.deleteById(id);
	}
	
	
	@GetMapping("/search/{nome}")
	public ResponseEntity<Page<Usuario>> findByName(@PathVariable String nome){
		Page<Usuario> list = service.findAllByName(nome);
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDto findById(@PathVariable String uuid) {
		return service.findById(uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDto update(@PathVariable String uuid, @RequestBody Usuario usuario) {
		return service.update(uuid, usuario);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto create(@RequestBody Usuario usuario) {
		return service.create(usuario);
	}

}
