package com.weedti.janehempshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cor;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.model.exception.ServerSideException;
import com.weedti.janehempshop.repository.CorRepository;

@Service
public class CorService {

	@Autowired
	private CorRepository repository;

	public List<Cor> buscaTodas() {
		return Optional.of(repository.findAll())
				.orElseThrow(() -> new ObjectNotFoundException("Nenhuma cor encontrada"));

	}

	public Cor cadastraCor(Cor cor) {

		return Optional.of(repository.save(cor)).orElseThrow(() -> new ServerSideException("Erro ao cadastrar cor"));

	}

	public void deletaCor(Integer idCor) {

		try {
			repository.delete(buscaCor(idCor));

		} catch (Exception e) {
			throw new ServerSideException("Erro ao deletar cor com o id " + idCor);

		}

	}

	public void atualizaCor(Integer idCor, Cor cor) {

		Optional.of(repository.save(setaValoresAtualizacaoCor(cor, buscaCor(idCor))))
				.orElseThrow(() -> new ServerSideException("Erro ao atualizar cor com o id " + idCor));

	}

	public Cor buscaCor(Integer idCor) {

		return repository.findById(idCor)
				.orElseThrow(() -> new ObjectNotFoundException("Nenhuma cor encontrada com o id " + idCor));
	}

	private Cor setaValoresAtualizacaoCor(Cor cor, Cor corBanco) {

		if (cor.getDescricao() != null && !cor.getDescricao().isEmpty())
			corBanco.setDescricao(cor.getDescricao());

		return corBanco;
	}

}
