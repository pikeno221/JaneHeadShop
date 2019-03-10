package com.weedti.janehempshop.model.database;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.weedti.janehempshop.enums.TamanhoProduto;
import com.weedti.janehempshop.model.abstracts.AbstractProduto;

@Entity
@Table(name = "Seda")
@JsonInclude(Include.NON_NULL)
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
