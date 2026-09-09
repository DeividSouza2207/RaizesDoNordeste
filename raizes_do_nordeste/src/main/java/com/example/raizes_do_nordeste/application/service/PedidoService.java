package com.example.raizes_do_nordeste.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.raizes_do_nordeste.domain.entity.Estoque;
import com.example.raizes_do_nordeste.domain.entity.ItemPedido;
import com.example.raizes_do_nordeste.domain.entity.Pedido;
import com.example.raizes_do_nordeste.domain.entity.Produto;
import com.example.raizes_do_nordeste.domain.entity.Unidade;
import com.example.raizes_do_nordeste.domain.repository.EstoqueRepository;
import com.example.raizes_do_nordeste.domain.repository.PedidoRepository;
import com.example.raizes_do_nordeste.domain.repository.ProdutoRepository;
import com.example.raizes_do_nordeste.domain.repository.UnidadeRepository;



@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository ;
	private final UnidadeRepository unidadeRepository;
	private final EstoqueRepository estoqueRepository;

	public PedidoService(
			PedidoRepository pedidoRepository,
			ProdutoRepository produtoRepository,
			UnidadeRepository unidadeRepository,
			EstoqueRepository estoqueRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.unidadeRepository = unidadeRepository;
		this.estoqueRepository = estoqueRepository;
	}
	
	@Transactional
	public Pedido criarPedido(Pedido pedido) {
		
		// ver se unidade existe
		Unidade unidade = unidadeRepository.findById(pedido.getUnidade().getId())
				.orElseThrow(() -> new RuntimeException("Unidade não foi encontrada"));
		
		// ver se a unidade está ativa 
		if(!unidade.isAtivo()) {
			throw new RuntimeException("A unidade está ativa");
		}
		
		// Ver se pedido possui itens
		if(pedido.getItens() == null || pedido.getItens().isEmpty()) {
			throw new RuntimeException("O pedido tem que possuir ao menos um item");
		}
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		List<ItemPedido> itensAdicionados= new ArrayList<>();
		
		// iterando os pedidos da lista itensPedido
		for (ItemPedido itemRecebido : pedido.getItens()) {
			
			if(itemRecebido.getQuantidade() == null ||
			   itemRecebido.getQuantidade() <=0) {
				throw new RuntimeException("A quantidade precisa ser maior que zero");
			}
			
		Long produtoId = itemRecebido.getProduto().getId();
		
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado" + produtoId));
		
		// verificar se o produto está disponível
		if (!produto.isAtivo()) {
			throw new RuntimeException("Produto não ativo" + produto.getNome());
		}
		
		// buscar produto no estoque da unidade
		Estoque estoque = estoqueRepository.findByUnidadeAndProduto(unidade, produto)
				.orElseThrow(() -> new RuntimeException("Produto não está disponível nesta unidade"));
		
		// verificar estoque
		if (estoque.getQuantidade() < itemRecebido.getQuantidade()) {
			throw new RuntimeException("Estoque não é suficiente" + produto.getNome());
		}
		
		BigDecimal valorUnitario = produto.getPreco();
		
		BigDecimal subtotal = valorUnitario.multiply(
				BigDecimal.valueOf(itemRecebido.getQuantidade())
		);
		// Criar o item processado		
		ItemPedido item = new ItemPedido();	
		
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(itemRecebido.getQuantidade());
		item.setValorUnitário(valorUnitario);
		item.setSubtotal(subtotal);
		
		itensAdicionados.add(item);
		
		// Diminuir o estoque
		estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
		
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
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	
	
		
	}
}
