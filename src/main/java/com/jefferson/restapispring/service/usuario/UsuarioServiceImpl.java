package com.jefferson.restapispring.service.usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jefferson.restapispring.dto.UsuarioResponse;
import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
	
	private final UsuarioRepository repository;

	@Override
	public List<UsuarioResponse> findAllUsers() {
		List<Usuario> entities = repository.findAll();
		return entities.stream().map(Usuario::convertToResponse).collect(Collectors.toList());
	}

	@Override
	public String deleteById(String id) {
		try {
			repository.deleteById(id);
			return "Registro deletado com sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "Ocorreu um erro ao deletar o registro";
		}
	}

	@Override
	public List<Usuario> findAllByName(String nome) {
		try {
			return repository.findAllByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao pesquisar o usuário por nome");
		}
	}

}
