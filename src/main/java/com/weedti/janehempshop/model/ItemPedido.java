package com.weedti.janehempshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;

	@NotNull(message = "quantidade e obrigatorio")
	private Integer quantidade;

	@NotNull(message = "preco e obrigatorio")
	private Double preco;
	
	
	
	

}
