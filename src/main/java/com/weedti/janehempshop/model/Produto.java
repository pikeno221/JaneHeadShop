package com.weedti.janehempshop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "produtos")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "nome e obrigatorio")
	@Column(length = 200)
	private String nome;
	
	private Cor cor;
	
	@NotNull(message = "categoria e obrigatoria")
	private Categoria categoria;
	
	@NotNull(message = "descricao e obrigatoria")
	private String descricao;
	
	private Double valor;
	
	private Integer qtdEstoque;
	

	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();


}
