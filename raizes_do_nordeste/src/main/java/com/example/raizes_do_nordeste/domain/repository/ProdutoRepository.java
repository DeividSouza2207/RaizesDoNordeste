package com.example.raizes_do_nordeste.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
