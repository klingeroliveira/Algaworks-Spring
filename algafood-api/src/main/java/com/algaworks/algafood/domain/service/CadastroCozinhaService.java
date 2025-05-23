package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("A cozinha de código %d está em uso.", cozinhaId));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado uma conzinha com código %d.", cozinhaId));
		}		
		
	}

}
