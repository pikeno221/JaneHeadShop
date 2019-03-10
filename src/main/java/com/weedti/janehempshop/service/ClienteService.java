package com.weedti.janehempshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cliente;
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

}
