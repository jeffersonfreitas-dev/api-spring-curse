package com.jefferson.restapispring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jefferson.restapispring.dto.UsuarioResponse;
import com.jefferson.restapispring.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {
	
	private final UsuarioService service;
	
	
	@GetMapping
	public List<UsuarioResponse> findAllUsers(){
		return service.findAllUsers();
	}

}
