package com.jefferson.restapispring.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTTokenAutenticacaoService {
	
	private final UsuarioRepository repository;

	private static final long EXPIRATION_TIME = 172800000;
	private static final String SECRET = "123456";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";
	
	
	//Gerando token e add no response header
	public void addAuthentication(HttpServletResponse response, String username ) throws IOException {
		String jwt = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + jwt;
		response.addHeader(HEADER_STRING, token);
		response.getWriter().write("{\"Authorization\": \""+token+"\"}"); //Escrever no corpo
	}
	
	
	
	//Retorna o user validado com token ou null se não for válido
	public Authentication getAuthentication(HttpServletRequest request) {
	
		String token = request.getHeader(HEADER_STRING);
		if(token == null) return null;
		
		String user = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody().getSubject();
		if(user == null) return null;
		
		Usuario usuario = repository.findByLogin(user)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());		
	}
	
	
}
