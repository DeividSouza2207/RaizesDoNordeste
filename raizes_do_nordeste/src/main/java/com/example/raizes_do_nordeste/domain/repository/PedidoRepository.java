package com.example.raizes_do_nordeste.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.domain.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
