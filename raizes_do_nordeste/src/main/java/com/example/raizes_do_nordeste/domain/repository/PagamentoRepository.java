package com.example.raizes_do_nordeste.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.domain.entity.Pagamento;
import com.example.raizes_do_nordeste.domain.entity.Pedido;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
	Optional<Pagamento> findByPedido(Pedido pedido);

}
