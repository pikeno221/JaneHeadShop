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

import com.weedti.janehempshop.model.Cor;
import com.weedti.janehempshop.service.CorService;

@RestController
@RequestMapping("/cores")
public class CorController {

	@Autowired
	private CorService service;

	@GetMapping()
	public ResponseEntity<List<Cor>> buscaTodas() {
		return ResponseEntity.ok(service.buscaTodas());

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cor> buscaCor(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscaCor(id));

	}

	@PostMapping()
	public ResponseEntity<Void> cadastraCor(@Valid @RequestBody Cor cor) {

		cor = service.cadastraCor(cor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cor.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizaCor(@PathVariable Integer id, @Valid @RequestBody Cor cor) {
		service.atualizaCor(id, cor);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletaCor(@PathVariable Integer id) {
		service.deletaCor(id);

		return ResponseEntity.noContent().build();

	}

}
