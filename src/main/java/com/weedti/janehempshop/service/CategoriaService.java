package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> buscaTodas() {
		return Optional.of(repository.findAll())
				.orElseThrow(() -> new ObjectNotFoundException("Nenhuma categoria cadastrada."));
	}

	public Categoria buscaCategoria(Integer id) {
		return Optional.of(repository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Nenhuma categoria com o id " + id + "encontrada")).get();
	}

	public Categoria cadastraCategoria(@Valid Categoria categoria) {
		return Optional.of(repository.save(categoria))
				.orElseThrow(() -> new ServerSideException("Erro ao cadastrar categoria"));

	}

	public void atualizaCategoria(Integer id, Categoria categoria) {
		Optional.of(repository.save(setaValoresAtualizacaoCategoria(categoria, buscaCategoria(id))))
				.orElseThrow(() -> new ServerSideException("Erro ao atualizar categoria com o id " + id));

	}

	public void deletaCategoria(Integer id) {

		try {
			repository.delete((Categoria) buscaCategoria(id));

		} catch (Exception e) {
			throw new ServerSideException("erro ao deletar categoria com o id " + id);
		}
	}
	
	private Categoria setaValoresAtualizacaoCategoria(Categoria categoria, Categoria corBanco) {

		if (categoria.getDescricao() != null && !categoria.getDescricao().isEmpty())
			corBanco.setDescricao(categoria.getDescricao());

		return corBanco;
	}

}
