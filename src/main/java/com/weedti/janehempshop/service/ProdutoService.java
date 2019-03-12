package com.weedti.janehempshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.model.Produto;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.repository.CategoriaRepository;
import com.weedti.janehempshop.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> buscaTodos() {

		return repository.findAll();
	}

	public ServiceResponse cadastraProduto(Produto produto) {

		try {

			repository.save(produto);
			return new ServiceResponse(produto.getId(), "sucesso ao gravar produto");

		} catch (Exception e) {
			throw new ServerSideException("Erro ao cadastrar produto");
		}

	}

	public void deletaProduto(Integer idProduto) {

		repository.delete(buscaProduto(idProduto));
	}

	public void atualizaProduto(Integer idProduto, Produto produto) {

		repository.save(setaValoresProdutoAtualizacao(produto, buscaProduto(idProduto)));

	}

	public Produto buscaProduto(Integer idProduto) {

		return repository.findById(idProduto)
				.orElseThrow(() -> new ObjectNotFoundException("Produto nao encontrada com o id: " + idProduto));
	}

	private Produto setaValoresProdutoAtualizacao(Produto produto, Produto produtoBanco) {

		if (produto.getNome() != null && !produto.getNome().isEmpty())
			produtoBanco.setNome(produto.getNome());

		if (produto.getDescricao() != null && !produto.getDescricao().isEmpty())
			produtoBanco.setDescricao(produto.getDescricao());

		if (produto.getCor() != null)
			produtoBanco.setCor(produto.getCor());

		if (produto.getValor() != null)
			produtoBanco.setValor(produto.getValor());

		return produtoBanco;

	}

	public List<Produto> buscaProdutosPorCategoria(Integer idCategoria) {
		Categoria categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new ObjectNotFoundException("categoria nao encontrada com o id: " + idCategoria));

		return repository.findByCategoria(categoria)
				.orElseThrow(() -> new ObjectNotFoundException("Nenhum produto encontrado com esta categoria."));
	}

}
