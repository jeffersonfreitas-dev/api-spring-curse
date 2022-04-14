package com.jefferson.restapispring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDto {
	
	private String uuid;
	private String login;
	private String nome;
	private String senha;

}
