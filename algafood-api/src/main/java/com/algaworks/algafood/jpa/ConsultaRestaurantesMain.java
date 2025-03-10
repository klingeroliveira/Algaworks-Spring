package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestaurantesMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

		List<Restaurante> listaRestaurante = restauranteRepository.listar();

		for (Restaurante restaurante : listaRestaurante) {
			System.out.printf("%s - %f - %s \n", restaurante.getNome(), restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNome());

			System.out.println(restaurante);
		}
	}
}