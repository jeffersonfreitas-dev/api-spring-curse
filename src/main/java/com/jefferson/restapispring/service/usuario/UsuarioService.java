package com.jefferson.restapispring.service.usuario;

import java.util.List;

import com.jefferson.restapispring.dto.UsuarioResponse;
import com.jefferson.restapispring.model.Usuario;

public interface UsuarioService {

	List<UsuarioResponse> findAllUsers();

	String deleteById(String id);

	List<Usuario> findAllByName(String nome);

}
