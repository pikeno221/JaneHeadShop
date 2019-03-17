package com.weedti.janehempshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cliente;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> buscaTodos() {
		return repository.findAll();
	}

	public Cliente cadastraCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	public void deletaCliente(Integer id) {
		repository.delete(buscaCliente(id));
	}

	public void atualizaCliente(Integer idCliente, Cliente cliente) {
		repository.save(setaValoresAtualizacaoCliente(cliente, buscaCliente(idCliente)));
	}

	public Cliente buscaCliente(Integer idCliente) {
		return repository.findById(idCliente)
				.orElseThrow(() -> new ServerSideException("Erro ao buscar cliente no banco"));

	}

	private Cliente setaValoresAtualizacaoCliente(Cliente cliente, Cliente clienteBanco) {

		if (cliente.getNome() != null && !cliente.getNome().isEmpty())
			clienteBanco.setNome(cliente.getNome());

		if (cliente.getEmail() != null && !cliente.getEmail().isEmpty())
			clienteBanco.setEmail(cliente.getEmail());

		if (cliente.getTelefone() != null && !cliente.getTelefone().isEmpty())
			clienteBanco.setTelefone(cliente.getTelefone());

		return clienteBanco;
	}

}
