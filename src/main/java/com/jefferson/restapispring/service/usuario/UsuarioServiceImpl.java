package com.jefferson.restapispring.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jefferson.restapispring.dto.UsuarioDto;
import com.jefferson.restapispring.model.Usuario;
import com.jefferson.restapispring.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
	
	private final UsuarioRepository repository;

	@Override
	public Page<Usuario> findAllUsers(Integer pagina) {
		PageRequest pageReq = PageRequest.of(pagina -1, 5, Sort.by("nome"));
		Page<Usuario> entities = repository.findAll(pageReq);
		return entities;
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
	public Page<Usuario> findAllByName(String nome) {
		
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("nome"));
		Page<Usuario> result = null;
		
		if(nome == null || nome.isEmpty() || nome.equalsIgnoreCase("undefided")) {
			result = repository.findAll(pageRequest);
		}else {
			result = repository.findAllPage(nome, pageRequest);
		}
		
		return result;
	}

	
	@Override
	public UsuarioDto findById(String uuid) {
		Usuario usuario = repository.findById(uuid)
				.orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
		return usuario.convertToDto();
	}

	
	@Override
	public UsuarioDto update(String uuid, Usuario usuario) {
		Usuario persisted = repository.findById(uuid)
				.orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
		persisted.setLogin(usuario.getLogin());
		persisted.setNome(usuario.getNome());
		persisted.setSenha(usuario.getSenha());
		persisted = repository.save(persisted);
		return persisted.convertToDto();
	}

	@Override
	public UsuarioDto create(Usuario usuario) {
		usuario = repository.save(usuario);
		
		return usuario.convertToDto();
	}

}
