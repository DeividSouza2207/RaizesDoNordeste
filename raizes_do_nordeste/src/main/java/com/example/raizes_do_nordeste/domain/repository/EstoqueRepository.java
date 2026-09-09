package com.example.raizes_do_nordeste.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.domain.entity.Estoque;
import com.example.raizes_do_nordeste.domain.entity.Produto;
import com.example.raizes_do_nordeste.domain.entity.Unidade;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

	Optional<Estoque> findByUnidadeAndProduto(Unidade unidade, Produto produto);
}
