package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class InclusaoEstadoMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Estado estado = new Estado();
		estado.setUf("PR");
		estado.setNome("Paraná");
		
		estadoRepository.salvar(estado);
		
		for (Estado estado2 : estadoRepository.listar()) {
			System.out.println(estado2);
		}

	}

}
