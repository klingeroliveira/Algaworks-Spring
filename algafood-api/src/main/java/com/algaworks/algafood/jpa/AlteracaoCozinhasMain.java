package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhasMain {

	public static void main(String[] args) {
		ApplicationContext aplApplicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhaRepository cozinhaRository = aplApplicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Japonesa");
		
		cozinha = cozinhaRository.salvar(cozinha);
		
		System.out.println(cozinha);
		
		List<Cozinha> listCozinhas = cozinhaRository.listar();

		for (Cozinha cozinha1 : listCozinhas) {
			System.out.println(cozinha1);
		}
	}
}
