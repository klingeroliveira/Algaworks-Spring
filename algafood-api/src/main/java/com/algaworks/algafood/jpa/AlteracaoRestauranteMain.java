package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.CozinhaRepositoryImpl;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRository = aplApplicationContext.getBean(RestauranteRepository.class);
		CozinhaRepository cozinhaRepository = aplApplicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("NovaCozinha");
		cozinha = cozinhaRepository.salvar(cozinha);
		
		Restaurante restaurante = new Restaurante();
		
		restaurante.setId(1L);
		restaurante.setNome("FoodAtuth");
		restaurante.setTaxaFrete(4.55);		
		restaurante.setCozinha(cozinha);
		
		restaurante = restauranteRository.salvar(restaurante);
		
		System.out.println(restaurante);
		
		List<Restaurante> listRestaurantes = restauranteRository.listar();

		for (Restaurante restaurante1 : listRestaurantes) {
			System.out.println(restaurante1);
		}
	}
}
