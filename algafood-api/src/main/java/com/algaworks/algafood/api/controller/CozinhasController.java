package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return cozinhaRepository.findAll();
	}

	/*
	 * @GetMapping(produces = MediaType.APPLICATION_XML_VALUE) public
	 * CozinhasXmlWrapper listarXML() { return new CozinhasXmlWrapper(listar()); }
	 */

	@GetMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

		if (!cozinha.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha.get());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

		if (!cozinhaAtual.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		// cozinhaAtual.setNome(cozinha.getNome());
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

		Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());

		return ResponseEntity.ok(cozinhaSalva);
	}

	@DeleteMapping(value = "/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinhaService.remover(cozinhaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
