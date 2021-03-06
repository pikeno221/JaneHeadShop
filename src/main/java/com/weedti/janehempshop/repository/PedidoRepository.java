package com.weedti.janehempshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weedti.janehempshop.model.Cliente;
import com.weedti.janehempshop.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	Optional<List<Pedido>> findByCliente(Cliente cliente);

}
