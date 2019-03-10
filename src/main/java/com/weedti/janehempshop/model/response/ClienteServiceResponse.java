package com.weedti.janehempshop.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteServiceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String mensagem;
	public ClienteServiceResponse(Integer id, String mensagem) {
		super();
		this.id = id;
		this.mensagem = mensagem;
	}

	
}
