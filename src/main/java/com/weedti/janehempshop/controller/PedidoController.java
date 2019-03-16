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

import com.weedti.janehempshop.model.Pedido;
import com.weedti.janehempshop.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@GetMapping()
	public ResponseEntity<List<Pedido>> buscaPedidos() {
		return ResponseEntity.ok(service.buscaTodos());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscaPedido(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscaPedido(id));

	}

	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<List<Pedido>> buscaPedidosPorIdCliente(@PathVariable Integer idCliente) {
		return ResponseEntity.ok(service.buscaPedidosPorIdCliente(idCliente));
		
	}
	
	@PostMapping()
	public ResponseEntity<Void> inserePedido(@Valid @RequestBody Pedido pedido) {
		pedido = service.salvaPedido(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizaPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
		service.atualizaPedido(id, pedido);

		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletaPedido(@PathVariable Integer id) {
		service.deletaPedido(id);

		return ResponseEntity.noContent().build();

	}

}
