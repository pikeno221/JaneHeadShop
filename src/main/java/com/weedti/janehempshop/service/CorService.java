package com.weedti.janehempshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weedti.janehempshop.model.Cor;
import com.weedti.janehempshop.model.exception.ObjectNotFoundException;
import com.weedti.janehempshop.repository.CorRepository;

@Service
public class CorService {

	@Autowired
	private CorRepository repository;

	public List<Cor> buscaTodas() {
		return repository.findAll();
	}

	public Cor cadastraCor(Cor cor) {
		return repository.save(cor);
	}

	public void deletaCor(Integer idCor) {
		repository.delete(buscaCor(idCor));
	}

	public void atualizaCor(Integer idCor, Cor cor) {
		repository.save(setaValoresAtualizacaoCor(cor, buscaCor(idCor)));
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
