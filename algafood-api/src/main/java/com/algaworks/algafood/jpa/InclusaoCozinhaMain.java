package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinhaRepository = aplApplicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha1 = new Cozinha();
		Cozinha cozinha2 = new Cozinha();
		cozinha1.setNome("Brasileira");
		cozinha2.setNome("Japonesa");
		
		cozinha1 = cozinhaRepository.salvar(cozinha1);
		cozinha2 = cozinhaRepository.salvar(cozinha2);
		
		System.out.println(cozinha1);
		System.out.println(cozinha2);
		
		List<Cozinha> listCozinhas = cozinhaRepository.listar();

		for (Cozinha cozinha : listCozinhas) {
			System.out.println(cozinha);
		}
	}
}
