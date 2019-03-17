package com.weedti.janehempshop.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Pedido;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteService clienteService;

	public List<Pedido> buscaTodos() {
		return repository.findAll();
	}

	public Pedido buscaPedido(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Nao existe pedido com o id " + id));

	}

	public List<Pedido> buscaPedidosPorIdCliente(Integer idCliente) {
		return repository.findByCliente(clienteService.buscaCliente(idCliente)).orElseThrow(
				() -> new ObjectNotFoundException("Nao existe pedido para este o cliente de id " + idCliente));

	}

	public Pedido salvaPedido(@Valid Pedido pedido) {
		return repository.save(pedido);
	}

	public void atualizaPedido(Integer id, Pedido pedido) {
		repository.save(setaValoresAtualizacaoPedido(pedido, buscaPedido(id)));
	}

	public void deletaPedido(Integer id) {
		repository.delete((Pedido) buscaPedido(id));
	}

	private Pedido setaValoresAtualizacaoPedido(Pedido pedido, Pedido pedidoBanco) {

		pedidoBanco.setDataAtualizacao(new Date());

		if (pedido.getPagamento() != null)
			pedidoBanco.setPagamento(pedido.getPagamento());

		if (pedido.getCliente() != null)
			pedidoBanco.setCliente(pedido.getCliente());

		if (pedido.getItens() != null)
			pedidoBanco.setItens(pedido.getItens());

		return pedidoBanco;
	}
}
