package com.jefferson.restapispring.service.usuario;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jefferson.restapispring.dto.UsuarioDto;
import com.jefferson.restapispring.model.Usuario;

public interface UsuarioService {

	Page<Usuario> findAllUsers(Integer pagina);

	String deleteById(String id);

	List<Usuario> findAllByName(String nome);

	UsuarioDto findById(String uuid);

	UsuarioDto update(String uuid, Usuario usuario);

	UsuarioDto create(Usuario usuario);

}
