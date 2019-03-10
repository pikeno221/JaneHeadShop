package com.weedti.janehempshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weedti.janehempshop.model.Seda;

@Repository
public interface SedaRepository extends JpaRepository<Seda, Integer>{

}
