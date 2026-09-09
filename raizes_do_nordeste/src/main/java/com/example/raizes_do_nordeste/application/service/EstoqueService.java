package com.example.raizes_do_nordeste.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.domain.entity.Estoque;
import com.example.raizes_do_nordeste.domain.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	private final EstoqueRepository estoqueRepository;
	
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}
	
	public Estoque salvar(Estoque estoque) {
		
		if(estoque.getQuantidade() < 0) {
			throw new RuntimeException("Quantidade não pode ser menor que zero");
		}
		
		return estoqueRepository.save(estoque);
	}
	
	public List<Estoque> listarTodos(){
		return estoqueRepository.findAll();
	}
	
	public Estoque buscarId(Long id) {
		return estoqueRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
	}

}
