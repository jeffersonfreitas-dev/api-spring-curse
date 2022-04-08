package com.jefferson.restapispring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.repository.UsuarioRepository;


public class JWTTokenAutenticacaoController extends AbstractAuthenticationProcessingFilter {
	
	private final UsuarioRepository repository;

	protected JWTTokenAutenticacaoController(String url, AuthenticationManager authenticationManager, UsuarioRepository repository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		this.repository = repository;
	}

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		Usuario user = mapper.readValue(request.getInputStream(), Usuario.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha(), user.getAuthorities()));
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		new JWTTokenAutenticacaoService(repository).addAuthentication(response, authResult.getName());
	}

}
