package com.weedti.janehempshop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> buscaTodas() {
		return repository.findAll();
	}

	public Categoria buscaCategoria(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria com o id " + id + " nao encontrada"));
	}

	public Categoria cadastraCategoria(@Valid Categoria categoria) {
		return repository.save(categoria);

	}

	public void atualizaCategoria(Integer id, Categoria categoria) {
		repository.save(setaValoresAtualizacaoCategoria(categoria, buscaCategoria(id)));

	}

	public void deletaCategoria(Integer id) {
		repository.delete((Categoria) buscaCategoria(id));
	}

	private Categoria setaValoresAtualizacaoCategoria(Categoria categoria, Categoria corBanco) {

		if (categoria.getDescricao() != null && !categoria.getDescricao().isEmpty())
			corBanco.setDescricao(categoria.getDescricao());

		return corBanco;
	}

}
