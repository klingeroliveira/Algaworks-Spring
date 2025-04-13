package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.infrastructure.repository.CidadeRepositoryImpl;

public class RemocaoEstadoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		CidadeRepositoryImpl cidadeRepositoryImpl = applicationContext.getBean(CidadeRepositoryImpl.class);
		
		Estado estadoRemover = estadoRepository.buscar(3l);
		
		List<Cidade> listCidades = cidadeRepositoryImpl.buscarCidadePorEstado(estadoRemover.getId());
		
		if(!listCidades.isEmpty()) {
			Estado estadoFix = new Estado();
			estadoFix.setNome("Minas Gerais");
			estadoFix.setUf("MG");
			
			estadoFix = estadoRepository.salvar(estadoFix);
			
			for (Cidade cidade : listCidades) {
				cidade.setEstado(estadoFix);
				cidade = cidadeRepositoryImpl.salvar(cidade);
				System.out.println(cidade);
			}			
		}
		
		
		estadoRepository.remover(estadoRemover.getId());
		
		for (Estado estado : estadoRepository.listar()) {
			System.out.println(estado);
		}
		

	}

}
