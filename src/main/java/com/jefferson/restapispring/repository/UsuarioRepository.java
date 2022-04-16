package com.jefferson.restapispring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jefferson.restapispring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String username);

	@Query("select u from Usuario u where u.nome like %?1%")
	List<Usuario> findAllByName(String nome);

	default Page<Usuario> findAllPage(String nome, PageRequest pageRequest){
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers
						.contains().ignoreCase());
		Example<Usuario> example = Example.of(usuario, exampleMatcher);
		return findAll(example, pageRequest);
	}
	
	

}
