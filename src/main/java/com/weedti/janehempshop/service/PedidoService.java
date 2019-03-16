package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Pedido;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteService clienteService;

	public List<Pedido> buscaTodos() {
		return Optional.of(repository.findAll())
				.orElseThrow(() -> new ObjectNotFoundException(" Nenhum Pedido Encontrado"));

	}

	public Pedido buscaPedido(Integer id) {
		return Optional.of(repository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Nao existe pedido com o id " + id)).get();

	}

	public List<Pedido> buscaPedidosPorIdCliente(Integer idCliente) {
		return Optional.of(repository.findByCliente(clienteService.buscaCliente(idCliente))).orElseThrow(
				() -> new ObjectNotFoundException("Nao existe pedido para este o cliente de id " + idCliente));

	}

	public Pedido salvaPedido(@Valid Pedido pedido) {

		return Optional.of(repository.save(pedido))
				.orElseThrow(() -> new ServerSideException("Erro ao gravar cliente"));

	}

	public void atualizaPedido(Integer id, Pedido pedido) {
		Optional.of(repository.save((Pedido) buscaPedido(id)))
				.orElseThrow(() -> new ObjectNotFoundException("Pedido com o id " + id + " nao foi encontrado. "));

	}

	public void deletaPedido(Integer id) {

		try {
			repository.delete((Pedido) buscaPedido(id));

		} catch (Exception e) {
			throw new ServerSideException(" Erro Critico. ");

		}

	}
}
