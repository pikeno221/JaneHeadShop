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

import com.weedti.janehempshop.model.Seda;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.service.SedaService;

@RestController
@RequestMapping(value = "/sedas")
public class SedaController {

	@Autowired
	private SedaService service;

	@GetMapping()
	public ResponseEntity<List<Seda>> buscaTodas() {

		return ResponseEntity.ok(service.buscaTodas());
	}

	@PostMapping()
	public ResponseEntity<Void> cadastraSeda(@Valid @RequestBody Seda seda) {

		ServiceResponse responseService = service.cadastraSeda(seda);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseService.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizaSeda(@PathVariable Integer idSeda, @Valid @RequestBody Seda seda) {
		service.atualizaSeda(seda, idSeda);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaSeda(@PathVariable Integer idSeda) {
		service.deletaSeda(idSeda);

		return ResponseEntity.noContent().build();
	}

}
