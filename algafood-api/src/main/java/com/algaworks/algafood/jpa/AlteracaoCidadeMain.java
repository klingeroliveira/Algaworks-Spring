package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class AlteracaoCidadeMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Cidade cidade = cidadeRepository.buscar(2l);		
		Estado estado = estadoRepository.buscar(3l);
		
		cidade.setNome("Belo Horizonte");
		cidade.setEstado(estado);
		
		cidade = cidadeRepository.salvar(cidade);
		
		for (Cidade cidade2 : cidadeRepository.listar()) {
			System.out.println(cidade2);			
		}
	}

}
