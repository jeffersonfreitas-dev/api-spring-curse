package com.jefferson.restapispring.exceptions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ApiError {
	
	private String mensagem;
	private Integer codigo;
	private List<String> errors = new ArrayList<>();
	private Timestamp timestamp;

	public void addError(String error){
		this.errors.add(error);
	}
}
