package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cor;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.model.response.ServiceResponse;
import com.weedti.janehempshop.repository.CorRepository;

@Service
public class CorService {

	@Autowired
	private CorRepository repository;

	public List<Cor> buscaTodas() {

		return repository.findAll();
	}

	public ServiceResponse cadastraCor(Cor cor) {

		try {
			cor = repository.save(cor);

			return new ServiceResponse(cor.getId(), "Inserido com sucesso");

		} catch (Exception e) {
			throw new ServerSideException("Erro ao salvar insercao");
		}

	}
	
	public void deletaCor(Integer idCor) {
		repository.delete(buscaCor(idCor));
	}

	public void atualizaCor(Integer idCor, Cor cor) {
		repository.save(setaValoresAtualizacaoCor(cor, buscaCor(idCor)));

	}

	public Cor buscaCor(Integer idCor) {

		Optional<Cor> cor = repository.findById(idCor);

		return cor.orElseThrow(() -> new ObjectNotFoundException("Cor nao encontrada"));

	}

	private Cor setaValoresAtualizacaoCor(Cor cor, Cor corBanco) {

		if (cor.getDescricao() != null && !cor.getDescricao().isEmpty())
			corBanco.setDescricao(cor.getDescricao());

		return corBanco;
	}

}
