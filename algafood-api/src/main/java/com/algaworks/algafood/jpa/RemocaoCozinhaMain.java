package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

public class RemocaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinhaRository = aplApplicationContext.getBean(CozinhaRepository.class);
		RestauranteRepositoryImpl restauranteRepositoryImpl  = aplApplicationContext.getBean(RestauranteRepositoryImpl.class);
		
		Cozinha cozinhaRemover = new Cozinha();
		cozinhaRemover.setId(2L);
		
		List<Restaurante> listRestaurante = restauranteRepositoryImpl.buscarPorCozinha(cozinhaRemover.getId());
		
		if (!listRestaurante.isEmpty()) {

			Cozinha cozinhaFix = new Cozinha();
			cozinhaFix.setNome("CozinhaFix");		
			cozinhaFix = cozinhaRository.salvar(cozinhaFix);
			
			for (Restaurante restaurante : listRestaurante) {
				System.out.println(restaurante);
				restaurante.setCozinha(cozinhaFix);
				restauranteRepositoryImpl.salvar(restaurante);
				System.out.println(restaurante);
			}
		}
		
		cozinhaRository.remover(cozinhaRemover.getId());

		for (Cozinha cozinha : cozinhaRository.listar()) {
			System.out.println(cozinha);
		}
	}
}
