package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.model.Produto;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.CategoriaRepository;
import com.weedti.janehempshop.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> buscaTodos() {

		return Optional.of(repository.findAll())
				.orElseThrow(() -> new ObjectNotFoundException("Nenhum Produto Encontrado. "));

	}

	public Produto cadastraProduto(Produto produto) {

		return Optional.of(repository.save(produto))
				.orElseThrow(() -> new ServerSideException("Erro ao gravar produto"));

	}

	public void deletaProduto(Integer idProduto) {

		try {
			repository.delete(buscaProduto(idProduto));

		} catch (Exception e) {
			throw new ServerSideException("Erro critico");
		}
	}

	public void atualizaProduto(Integer idProduto, Produto produto) {
		;
		Optional.of(repository.save(setaValoresAtualizacaoProduto(produto, buscaProduto(idProduto))))
				.orElseThrow(() -> new ServerSideException("Erro ao atualizar produto "));

	}

	public Produto buscaProduto(Integer idProduto) {

		return repository.findById(idProduto)
				.orElseThrow(() -> new ObjectNotFoundException("Produto nao encontrada com o id: " + idProduto));
	}

	public List<Produto> buscaProdutosPorCategoria(Integer idCategoria) {
		Categoria categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new ObjectNotFoundException("categoria nao encontrada com o id: " + idCategoria));

		return repository.findByCategoria(categoria)
				.orElseThrow(() -> new ObjectNotFoundException("Nenhum produto encontrado com esta categoria."));
	}

	private Produto setaValoresAtualizacaoProduto(Produto produto, Produto produtoBanco) {

		if (produto.getNome() != null && !produto.getNome().isEmpty())
			produtoBanco.setNome(produto.getNome());

		if (produto.getCor() != null)
			produtoBanco.setCor(produto.getCor());

		if (produto.getCategoria() != null)
			produtoBanco.setCategoria(produto.getCategoria());

		if (produto.getDescricao() != null && !produto.getDescricao().isEmpty())
			produtoBanco.setDescricao(produto.getDescricao());

		if (produto.getValor() != null)
			produtoBanco.setValor(produto.getValor());

		if (produto.getQtdEstoque() != null)
			produtoBanco.setQtdEstoque(produto.getQtdEstoque());

		return produtoBanco;
	}

}
