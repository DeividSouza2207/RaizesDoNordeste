package com.example.raizes_do_nordeste.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.domain.entity.Unidade;
import com.example.raizes_do_nordeste.domain.repository.UnidadeRepository;

@Service
public class UnidadeService {
	
	private final UnidadeRepository unidadeRepository;
	
	public UnidadeService(UnidadeRepository unidadeRepository) {
			this.unidadeRepository = unidadeRepository;
	}
	
	public Unidade salvar(Unidade unidade) {
		return unidadeRepository.save(unidade);
	}
	
	public List<Unidade> listarTodas() {
		return unidadeRepository.findAll();
	}
	
	public Unidade buscarId(Long id) {
		return unidadeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
	}
	

}
