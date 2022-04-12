package com.jefferson.restapispring.service.usuario;

import java.util.List;

import com.jefferson.restapispring.dto.UsuarioResponse;

public interface UsuarioService {

	List<UsuarioResponse> findAllUsers();

	String deleteById(String id);

}
