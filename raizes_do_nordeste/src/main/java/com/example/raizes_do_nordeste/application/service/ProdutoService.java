package com.example.raizes_do_nordeste.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.domain.entity.Produto;
import com.example.raizes_do_nordeste.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto salvar(Produto produto) {
		
		if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("O preço tem ser maior do que zero");
		}
		return produtoRepository.save(produto);
	}
		
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
		
	}
	
	public Produto buscarId(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	
	}
}
