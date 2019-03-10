package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cliente;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.model.response.ClienteServiceResponse;
import com.weedti.janehempshop.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> buscarTodos() {

		return repository.findAll();
	}

	public ClienteServiceResponse cadastraCliente(Cliente cliente) {

		try {

			repository.save(cliente);
			return new ClienteServiceResponse(cliente.getId(), "sucesso ao gravar cliente");

		} catch (Exception e) {
			throw new ServerSideException("Erro ao realizar a inserção do cliente! ");

		}

	}

	public void deletaCliente(Integer id) {
		repository.delete(buscaCliente(id));

	}

	public void atualizaCliente(Cliente cliente, Integer id) {

		repository.save(setaValoresClienteAtualizacao(cliente, buscaCliente(id)));
	}

	public Cliente buscaCliente(Integer id) {

		Optional<Cliente> cliente = repository.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException("cliente não encontrado. Id: " + id));

	}

	private Cliente setaValoresClienteAtualizacao(Cliente cliente, Cliente clienteBanco) {

		if (cliente.getNome() != null && !cliente.getNome().isEmpty())
			clienteBanco.setNome(cliente.getNome());

		if (cliente.getEmail() != null && !cliente.getEmail().isEmpty())
			clienteBanco.setEmail(cliente.getEmail());

		if (cliente.getTelefone() != null && !cliente.getTelefone().isEmpty())
			clienteBanco.setTelefone(cliente.getTelefone());

		return clienteBanco;

	}

}
