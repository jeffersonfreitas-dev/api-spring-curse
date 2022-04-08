package com.jefferson.restapispring.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	@Column(name = "login", unique = true, length = 30)
	private String login;
	@Column(name = "nome", unique = false, length = 30)
	private String nome;
	@Column(name = "senha", unique = false, length = 100)
	private String senha;
	@Column(name = "token", unique = false, length = 255)
	private String token;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority admin = new SimpleGrantedAuthority("ROLE_admin");
		return Arrays.asList(admin);
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
