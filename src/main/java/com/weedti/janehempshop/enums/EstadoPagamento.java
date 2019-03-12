package com.weedti.janehempshop.enums;

import com.weedti.janehempshop.model.exception.ObjectNotFoundException;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento(Integer codigo, String descricao) {

	}

	public Integer getCodigo() {
		return this.codigo;

	}

	public String getDescricao() {
		return this.descricao;

	}

	public static EstadoPagamento toEnum(Integer codigo) {

		if (codigo == null)
			return null;

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo()))
				return x;

		}

		throw new ObjectNotFoundException("Estado Pagamento nao encontrado");

	}

}
