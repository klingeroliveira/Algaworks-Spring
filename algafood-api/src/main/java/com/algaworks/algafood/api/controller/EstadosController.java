package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadosController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping(value = "{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if (!estado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(estado.get());
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		estado = cadastroEstadoService.salvar(estado);
		return ResponseEntity.ok(estado);
	}
	
	@DeleteMapping(value = "/{id}")	
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			cadastroEstadoService.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/{id}")	
	public ResponseEntity<?> atualizar(@RequestBody Estado estado, @PathVariable Long id) {
		try {			
			Optional<Estado> estadoAtual = estadoRepository.findById(id);
			
			if (!estadoAtual.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			
			Estado estadoSalvo = cadastroEstadoService.salvar(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvo);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}