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

import com.weedti.janehempshop.model.database.Cliente;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> buscaClientes() {
		return ResponseEntity.ok(service.buscarTodos());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaCliente(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscaCliente(id));

	}

	@PostMapping
	public ResponseEntity<Void> cadastraCliente(@Valid @RequestBody Cliente cliente) {
		ServiceResponse clienteResponse = service.cadastraCliente(cliente);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteResponse.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizaCliente(@Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
		service.atualizaCliente(cliente, id);

		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCliente(@PathVariable Integer id) {
		service.deletaCliente(id);
		
		return ResponseEntity.noContent().build();
		
		
	}
	

}
