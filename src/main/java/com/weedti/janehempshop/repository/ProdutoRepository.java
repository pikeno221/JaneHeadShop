package com.weedti.janehempshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weedti.janehempshop.model.Categoria;
import com.weedti.janehempshop.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Optional<List<Produto>> findByCategoria(Categoria categoria);
}
