package com.weedti.janehempshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.database.Seda;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.repository.SedaRepository;

@Service
public class SedaService {

	@Autowired
	private SedaRepository repository;

	public List<Seda> buscaTodas() {

		return repository.findAll();
	}

	public ServiceResponse cadastraSeda(Seda seda) {

		try {

			repository.save(seda);
			return new ServiceResponse(seda.getId(), "sucesso ao gravar seda");

		} catch (Exception e) {
			throw new ServerSideException("Erro ao cadastrar seda");
		}

	}

	public void deletaSeda(Integer idSeda) {

		repository.delete(buscaSeda(idSeda));
	}

	public void atualizaSeda(Seda seda, Integer idSeda) {

		repository.save(setaValoresSedaAtualizacao(seda, buscaSeda(idSeda)));

	}

	private Seda buscaSeda(Integer idSeda) {

		return repository.findById(idSeda)
				.orElseThrow(() -> new ObjectNotFoundException("Seda nao encontrada com o id: " + idSeda));
	}

	private Seda setaValoresSedaAtualizacao(Seda seda, Seda sedaBanco) {

		if (seda.getNome() != null && !seda.getNome().isEmpty())
			sedaBanco.setNome(seda.getNome());

		if (seda.getDescricao() != null && !seda.getDescricao().isEmpty())
			sedaBanco.setDescricao(seda.getDescricao());

		if (seda.getCor() != null)
			sedaBanco.setCor(seda.getCor());

		if (seda.getValor() != null)
			sedaBanco.setValor(seda.getValor());

		return sedaBanco;

	}

}
