package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cliente;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> buscaTodos() {

		return Optional.of(repository.findAll())
				.orElseThrow(() -> new ObjectNotFoundException("Nenhum Cliente encontrado"));

	}

	public Cliente cadastraCliente(Cliente cliente) {
		return Optional.of(repository.save(cliente))
				.orElseThrow(() -> new ServerSideException("Erro ao gravar cliente no banco"));

	}

	public void deletaCliente(Integer id) {
		try {
			repository.delete(buscaCliente(id));

		} catch (NullPointerException e) {
			throw new ServerSideException("Erro crtico");

		}
	}

	public void atualizaCliente(Integer idCliente, Cliente cliente) {
		buscaCliente(idCliente);

		Optional.of(repository.save(setaValoresAtualizacaoCliente(cliente, buscaCliente(idCliente))))
				.orElseThrow(() -> new ServerSideException("Erro ao atualizar Cliente"));

	}

	public Cliente buscaCliente(Integer idCliente) {

		return Optional.of(repository.findById(idCliente))
				.orElseThrow(() -> new ServerSideException("Erro ao buscar cliente no banco")).get();

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
