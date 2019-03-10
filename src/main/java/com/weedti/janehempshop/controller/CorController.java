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

import com.weedti.janehempshop.model.database.Cor;
import com.weedti.janehempshop.model.response.ServiceResponse;
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

	@PostMapping()
	public ResponseEntity<Void> cadastraCor(@Valid @RequestBody Cor cor) {

		ServiceResponse responseService = service.cadastraCor(cor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseService.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizaCor(@PathVariable Integer idCor, @Valid @RequestBody Cor cor) {
		service.atualizaCor(cor, idCor);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletaCor(@PathVariable Integer idCor) {
		service.deletaCor(idCor);

		return ResponseEntity.noContent().build();

	}

}
