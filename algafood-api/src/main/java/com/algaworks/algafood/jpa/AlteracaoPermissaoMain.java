package com.algaworks.algafood.jpa;

import java.security.Permission;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class AlteracaoPermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		for (Permissao permissao2 : permissaoRepository.listar()) {
			System.out.println(permissao2);
		}
		
		Permissao permissao = permissaoRepository.buscar(2l);
		
		permissao.setDescricao("Alteracao descricao");
		
		permissaoRepository.salvar(permissao);
		
		for (Permissao permissao2 : permissaoRepository.listar()) {
			System.out.println(permissao2);
		}
	}

}
