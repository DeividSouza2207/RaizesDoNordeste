package com.example.raizes_do_nordeste.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.raizes_do_nordeste.domain.entity.Pagamento;
import com.example.raizes_do_nordeste.domain.entity.Pedido;
import com.example.raizes_do_nordeste.domain.enums.StatusPagamento;
import com.example.raizes_do_nordeste.domain.enums.StatusPedido;
import com.example.raizes_do_nordeste.domain.repository.PagamentoRepository;
import com.example.raizes_do_nordeste.domain.repository.PedidoRepository;

@Service
public class PagamentoService {
	
	private final PagamentoRepository pagamentoRepository;
	private final PedidoRepository pedidoRepository;
	
	public PagamentoService(
			PagamentoRepository pagamentoRepository,
			PedidoRepository pedidoRepository) {
		
		this.pagamentoRepository = pagamentoRepository;
		this.pedidoRepository = pedidoRepository;
	}
	
	@Transactional
	public Pagamento efetuarPagamento(Long pedidoId) {
		
		// buscar o pedido
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
		
		if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
			throw new RuntimeException("Pedido aguardando pagamento");
		}
		
		// Verificar se este pedido já foi pago
		if (pagamentoRepository.findByPedido(pedido).isPresent()) {
			throw new RuntimeException("Este pedido já possui pagamento");
		}
		
		// criar o pagamento
		Pagamento pagamento = new Pagamento();
		
		pagamento.setPedido(pedido);
		pagamento.setValor(pedido.getValorTotal());
		pagamento.setDataHora(LocalDateTime.now());
		
		// simular o gateway
		pagamento.setStatus(StatusPagamento.APROVADO);
		
		// atualizar o status dos pedido
		pedido.setStatus(StatusPedido.PAGAMENTO_APROVADO);
		
		// salvar o pedido
		pedidoRepository.save(pedido);
		
		return pagamentoRepository.save(pagamento);
				
	}

}
