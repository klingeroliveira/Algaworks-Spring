package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRository = aplApplicationContext.getBean(RestauranteRepository.class);

		Restaurante restaurante = restauranteRository.buscar(2L);

		System.out.println(restaurante);
	}
}