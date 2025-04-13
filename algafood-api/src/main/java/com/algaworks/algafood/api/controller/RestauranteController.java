package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteRepository.buscar(id);

		if (restaurante == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurante);
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/{restauranteId}")
	public ResponseEntity<?> remover(@PathVariable Long restauranteId) {
		try {
			cadastroRestauranteService.remover(restauranteId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(value = "/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

			if (restauranteAtual == null) {
				return ResponseEntity.notFound().build();
			}

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

			restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
			return ResponseEntity.ok(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> atualizacaoParcial(@PathVariable Long id, @RequestBody Map<String, Object> dadosAtualizacao) {
		Restaurante restauranteAtual = restauranteRepository.buscar(id);
		
		mergePropriedades(dadosAtualizacao, restauranteAtual);
		
		return atualizar(id, restauranteAtual);
	}

	private void mergePropriedades(Map<String, Object> dadosAtualizacao, Restaurante restauranteAtual) {
		/**
		 * ObjectMapper para converter os valores que quero atualizar (dadosAtualizacao), 
		 * 	para o mesmo tipo de dado da propriedade correspondente, na entidade
		 * criando uma entidade com os valores convertidos (restauranteDadosAtualizacao)
		 * */
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteDadosAtualizacao = objectMapper.convertValue(dadosAtualizacao, Restaurante.class);
		
		//iterando o Map para buscar as propriedades que serão utilizadas na atualização
		dadosAtualizacao.forEach((nomePropriedade, valorPropriedade) -> {
			
			//findField pesquisa e retorna a propriedade com mesmo nome passado na requisição(dadosAtualizacao)
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			
			//torna as propriedade publicas nesse contexto
			field.setAccessible(true);
			
			//getField recupera o valor convertido da mesma propriedade passada na requisição
			Object novoValor = ReflectionUtils.getField(field, restauranteDadosAtualizacao);
			
			//setField atualiza as propriedades do restauranteAtual, tendo como referência o nome da propriedade(field) e o valor da propriedade(novoValor)
			ReflectionUtils.setField(field, restauranteAtual, novoValor);
		});
	}

}
