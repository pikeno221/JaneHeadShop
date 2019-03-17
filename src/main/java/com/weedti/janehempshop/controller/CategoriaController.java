package com.weedti.janehempshop.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping()
	public ResponseEntity<List<Categoria>> buscaCategorias() {
		return ResponseEntity.ok(service.buscaTodas());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscaCategoria(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscaCategoria(id));

	}

	@PostMapping()
	public ResponseEntity<Void> cadastraCategoria(@Valid @RequestBody Categoria categoria) {
		categoria = service.cadastraCategoria(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> atualizaCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
		service.atualizaCategoria(id, categoria);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCategoria(@PathVariable Integer id) {
		service.deletaCategoria(id);

		return ResponseEntity.noContent().build();

	}

}
