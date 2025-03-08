package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class InclusaoCidadeMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		
		estado.setNome("Minas Gerais");
		estado.setUf("MG");
		estado = estadoRepository.salvar(estado);		
		
		System.out.println(estado);
		
		cidade.setNome("Juiz de Fora");
		cidade.setEstado(estado);		
		cidade = cidadeRepository.salvar(cidade);
		
		System.out.println(cidade);
		
		for (Cidade cidade2 : cidadeRepository.listar()) {
			System.out.println(cidade2);
		}

	}

}
