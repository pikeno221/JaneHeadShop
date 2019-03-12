package com.weedti.janehempshop.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weedti.janehempshop.model.Produto;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping()
	public ResponseEntity<List<Produto>> buscaTodas() {
		return ResponseEntity.ok(service.buscaTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorIdProduto(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscaProduto(id));

	}

	@PostMapping()
	public ResponseEntity<Void> cadastraProduto(@Valid @RequestBody Produto produto) {

		ServiceResponse responseService = service.cadastraProduto(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseService.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizaProduto(@PathVariable Integer idProduto, @Valid @RequestBody Produto produto) {
		service.atualizaProduto(idProduto, produto);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaProduto(@PathVariable Integer idProduto) {
		service.deletaProduto(idProduto);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<List<Produto>> buscarProdutoPorCategoria(@PathVariable Integer idCategoria) {
		return ResponseEntity.ok(service.buscaProdutosPorCategoria(idCategoria));
		
	}

}
