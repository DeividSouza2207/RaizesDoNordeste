package com.example.raizes_do_nordeste.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.raizes_do_nordeste.api.exception.RecursoNaoEncontradoException;
import com.example.raizes_do_nordeste.api.exception.RegraDeNegocioException;
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
	public Pagamento efetuarPagamento(Long pedidoId, String resultado) {
		
		// buscar o pedido
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
		
		if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
			throw new RegraDeNegocioException("pagamento já foi realizado");
		}
		
		// Verificar se este pedido já foi pago
		if (pagamentoRepository.findByPedido(pedido).isPresent()) {
			throw new RegraDeNegocioException("Este pedido já possui pagamento");
		}
		
		// criar o pagamento
		Pagamento pagamento = new Pagamento();
		
		pagamento.setPedido(pedido);
		pagamento.setValor(pedido.getValorTotal());
		pagamento.setDataHora(LocalDateTime.now());
		
		if ("APROVADO".equalsIgnoreCase(resultado)) {
			
			pagamento.setStatus(StatusPagamento.APROVADO);
			pedido.setStatus(StatusPedido.PAGAMENTO_APROVADO);
		}
		
		else if ("NEGADO".equalsIgnoreCase(resultado)) {
			
			pagamento.setStatus(StatusPagamento.NEGADO);
			pedido.setStatus(StatusPedido.CANCELADO);
		}
		
		else {
			throw new RegraDeNegocioException("Opção inválida. Digite APROVADO ou NEGADO.");
		}
		
		// salvar o pedido
		pedidoRepository.save(pedido);
		
		return pagamentoRepository.save(pagamento);
				
	}

}
