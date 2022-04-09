package com.jefferson.restapispring.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jefferson.restapispring.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UsuarioDetailsService service;
	private final UsuarioRepository repository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Ativando proteção contra usuario que não estão validados token
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.disable().authorizeRequests().antMatchers("/**").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/").permitAll()
			.anyRequest().authenticated()
			.and()
			.logout().logoutSuccessUrl("/index")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			.addFilterBefore(new JWTTokenAutenticacaoController("/login", authenticationManager(), repository), 
					UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTApiAutenticacaoFilter(repository), UsernamePasswordAuthenticationFilter.class);

			
	}

}
