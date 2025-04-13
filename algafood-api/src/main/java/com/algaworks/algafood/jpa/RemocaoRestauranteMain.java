package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class RemocaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRository = aplApplicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		
		System.out.println(restauranteRository.buscar(restaurante.getId()));

		restauranteRository.remover(restaurante.getId());

		List<Restaurante> listRestaurantes = restauranteRository.listar();

		for (Restaurante restaurante1 : listRestaurantes) {
			System.out.println(restaurante1);
		}
	}
}
