package com.jefferson.restapispring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jefferson.restapispring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String username);

	@Query("select u from Usuario u where u.nome like %?1%")
	List<Usuario> findAllByName(String nome);

}
