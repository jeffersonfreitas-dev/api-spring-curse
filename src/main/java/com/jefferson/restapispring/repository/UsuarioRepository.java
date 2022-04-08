package com.jefferson.restapispring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jefferson.restapispring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String username);

}
