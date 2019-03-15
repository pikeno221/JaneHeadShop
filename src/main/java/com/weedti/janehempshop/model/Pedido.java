package com.weedti.janehempshop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Null
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataPedido;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAtualizacao;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@NotNull(message = "cliente e obrigatorio")
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	/*
	 * public Double getTotal() { double total = 0;
	 * 
	 * for (ItemPedido x : this.produtos) { total += this.produtos.getSubTotal(); }
	 * 
	 * return total; }
	 * 
	 */
}
