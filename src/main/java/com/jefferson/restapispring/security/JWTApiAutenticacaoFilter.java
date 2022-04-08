package com.jefferson.restapispring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.jefferson.restapispring.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTApiAutenticacaoFilter extends GenericFilterBean{
	
	private final UsuarioRepository repository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Authentication auth = new JWTTokenAutenticacaoService(repository).getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

}
