package com.example.raizes_do_nordeste.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.raizes_do_nordeste.api.dto.CriarPedidoRequest;
import com.example.raizes_do_nordeste.api.dto.ItemPedidoRequest;
import com.example.raizes_do_nordeste.api.dto.ItemPedidoResponse;
import com.example.raizes_do_nordeste.api.dto.PedidoResponse;
import com.example.raizes_do_nordeste.api.exception.RecursoNaoEncontradoException;
import com.example.raizes_do_nordeste.api.exception.RegraDeNegocioException;
import com.example.raizes_do_nordeste.domain.entity.Estoque;
import com.example.raizes_do_nordeste.domain.entity.ItemPedido;
import com.example.raizes_do_nordeste.domain.entity.Pedido;
import com.example.raizes_do_nordeste.domain.entity.Produto;
import com.example.raizes_do_nordeste.domain.entity.Unidade;
import com.example.raizes_do_nordeste.domain.entity.Usuario;
import com.example.raizes_do_nordeste.domain.enums.StatusPedido;
import com.example.raizes_do_nordeste.domain.repository.EstoqueRepository;
import com.example.raizes_do_nordeste.domain.repository.PedidoRepository;
import com.example.raizes_do_nordeste.domain.repository.ProdutoRepository;
import com.example.raizes_do_nordeste.domain.repository.UnidadeRepository;
import com.example.raizes_do_nordeste.domain.repository.UsuarioRepository;



@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository ;
	private final UnidadeRepository unidadeRepository;
	private final EstoqueRepository estoqueRepository;
	private final UsuarioRepository usuarioRepository;

	public PedidoService(
			PedidoRepository pedidoRepository,
			ProdutoRepository produtoRepository,
			UnidadeRepository unidadeRepository,
			EstoqueRepository estoqueRepository,
			UsuarioRepository usuarioRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.unidadeRepository = unidadeRepository;
		this.estoqueRepository = estoqueRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public Pedido criarPedido(CriarPedidoRequest request) {
		
		Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    Long usuarioId = Long.valueOf(authentication.getName());
	   
	    Usuario cliente = usuarioRepository.findById(usuarioId)
	    		
	            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
		
		// ver se unidade existe 
		Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não foi encontrada"));
		
		// ver se a unidade está ativa 
		if(!unidade.isAtivo()) {
			throw new RegraDeNegocioException("A unidade está inativa");
		}
		
		if(request.getCanalPedido() == null) {
			throw new RuntimeException("O canal do pedido é obrigatório");
		}
		
		// Ver se pedido possui itens
		if(request.getItens() == null || request.getItens().isEmpty()) {
			throw new RuntimeException("O pedido tem que possuir ao menos um item");
		}
		
		Pedido pedido = new Pedido();
		
		pedido.setCliente(cliente);
		pedido.setUnidade(unidade);
		pedido.setCanalPedido(request.getCanalPedido());
		pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		List<ItemPedido> itensAdicionados= new ArrayList<>();
		
		// iterando os pedidos da lista itensPedido
		for (ItemPedidoRequest itemRequest : request.getItens()) {
			
			if(itemRequest.getQuantidade() == null ||
			   itemRequest.getQuantidade() <=0) {
				throw new RuntimeException("A quantidade precisa ser maior que zero");
			}
			
		Long produtoId = itemRequest.getProdutoId();
		
		
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + produtoId));
		
		// verificar se o produto está disponível
		if (!produto.isAtivo()) {
			throw new RegraDeNegocioException("Produto inativo" + produto.getNome());
		}
		
		// buscar produto no estoque da unidade
		Estoque estoque = estoqueRepository.findByUnidadeAndProduto(unidade, produto)
				.orElseThrow(() -> new RegraDeNegocioException("Produto não está disponível nesta unidade"));
		
		// verificar estoque
		if (estoque.getQuantidade() < itemRequest.getQuantidade()) {
			throw new RegraDeNegocioException("Estoque insuficiente para o produto: " + produto.getNome());
		}
		
		BigDecimal valorUnitario = produto.getPreco();
		
		BigDecimal subtotal = valorUnitario.multiply(
				BigDecimal.valueOf(itemRequest.getQuantidade())
		);
		// Criar o item processado		
		ItemPedido item = new ItemPedido();	
		
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(itemRequest.getQuantidade());
		item.setValorUnitário(valorUnitario);
		item.setSubtotal(subtotal);
		
		itensAdicionados.add(item);
		
		// Diminuir o estoque
		estoque.setQuantidade(estoque.getQuantidade() - itemRequest.getQuantidade());
		
		estoqueRepository.save(estoque);
		
		// Somar ao valor total
		valorTotal = valorTotal.add(subtotal);
		}	
		// Configurar os itens adicionados
		pedido.setItens(itensAdicionados);
		
		//Definir o valor total calculado pelo servidor
		pedido.setValorTotal(valorTotal);
		
		// salvar o pedido
		return pedidoRepository.save(pedido);
		}
	
		
	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
		
	}
	
	public Pedido buscarPorId(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
	
	
	}
	
	@Transactional
	public Pedido atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
		
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
		
		StatusPedido statusAtual = pedido.getStatus();
		
		if(!transicaoPermitida(statusAtual, novoStatus)) {
			throw new RegraDeNegocioException(
					"Não é possível alterar o pedido de" + statusAtual + "para" + novoStatus);
		}
		
		pedido.setStatus(novoStatus);
		
		return pedidoRepository.save(pedido);
		
	}
	
	private boolean transicaoPermitida(StatusPedido statusAtual, StatusPedido novoStatus) {
		
		if (statusAtual == StatusPedido.PAGAMENTO_APROVADO) {
			
			return novoStatus == StatusPedido.EM_PREPARACAO ||
					novoStatus == StatusPedido.CANCELADO;
		}
		
		if (statusAtual == StatusPedido.EM_PREPARACAO) {
			
			return novoStatus == StatusPedido.PRONTO;
		}
		
		if (statusAtual == StatusPedido.PRONTO) {
			
			return novoStatus == StatusPedido.ENTREGUE;
		}
		
		return false;
	}
	
	public PedidoResponse converterParaResponse(Pedido pedido) {
		
		List<ItemPedidoResponse> itens = pedido.getItens()
				.stream()
				.map(item -> new ItemPedidoResponse(
						item.getProduto().getId(),
						item.getProduto().getNome(),
						item.getQuantidade(),
						item.getValorUnitário(),
						item.getSubtotal())
						)
				.toList();
		
		return new PedidoResponse(
				pedido.getId(),
				pedido.getCliente().getId(),
				pedido.getUnidade().getId(),
				pedido.getUnidade().getNome(),
				pedido.getCanalPedido(),
				pedido.getStatus(),
				pedido.getValorTotal(),
				pedido.getDataCriacao(),
				itens);
	}
}

