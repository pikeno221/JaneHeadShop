package com.weedti.janehempshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Seda")
public class Seda extends AbstractProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TamanhoProduto tamanho;

	public TamanhoProduto getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoProduto tamanho) {
		this.tamanho = tamanho;
	}

}
