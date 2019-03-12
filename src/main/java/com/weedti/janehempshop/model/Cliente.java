package com.weedti.janehempshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "clientes")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	

	@Column(length = 100)
	@NotNull(message = "nome obrigatorio")
	private String nome;
	
	@Column(length = 100)
	@NotNull(message = "email e obrigatorio")
	private String email;
	
	@Column(length = 25)
	@NotNull(message = "telefone e obrigatorio")
	private String telefone;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente(String nome, String email, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	public Cliente() {
		
	}
	
	

}
