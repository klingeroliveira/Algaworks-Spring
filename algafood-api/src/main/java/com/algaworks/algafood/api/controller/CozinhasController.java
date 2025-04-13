package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController /* substitui as duas anotações: @Controller @ResponseBody */
@RequestMapping("/cozinhas")
public class CozinhasController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	/*
	 * @GetMapping(produces = MediaType.APPLICATION_XML_VALUE) public
	 * CozinhasXmlWrapper listarXML() { return new CozinhasXmlWrapper(listar()); }
	 */

	@GetMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

		if (cozinhaAtual == null) {
			return ResponseEntity.notFound().build();
		}

		// cozinhaAtual.setNome(cozinha.getNome());
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		cadastroCozinhaService.salvar(cozinhaAtual);

		return ResponseEntity.ok(cozinhaAtual);
	}

	@DeleteMapping(value = "/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinhaService.remover(cozinhaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
