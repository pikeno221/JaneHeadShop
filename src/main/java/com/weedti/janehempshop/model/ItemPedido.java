package com.weedti.janehempshop.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
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

	
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;

	@NotNull(message = "quantidade e obrigatorio")
	private Integer quantidade;

	@NotNull(message = "preco e obrigatorio")
	private Double preco;

	public ItemPedido(ItemPedidoPK id, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id.setPedido(id.getPedido());
		this.id.setProduto(id.getProduto());
		this.quantidade = quantidade;
		this.preco = preco;

	}

	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}

}
