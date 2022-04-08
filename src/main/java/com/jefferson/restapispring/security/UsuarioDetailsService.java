package com.jefferson.restapispring.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioDetailsService implements UserDetailsService{
	
	private final UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = repository.findByLogin(username)
				.orElseThrow(() -> new IllegalArgumentException("Nome de login não encontrado"));
		return new User(user.getLogin(), user.getPassword(), user.getAuthorities());
	}

}
