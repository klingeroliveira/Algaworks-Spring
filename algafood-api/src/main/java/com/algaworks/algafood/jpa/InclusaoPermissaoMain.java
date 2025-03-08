package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class InclusaoPermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao = new Permissao();
		permissao.setNome("Incluir permissao");
		permissao.setDescricao("Permite incluir cadastros de permissao.");

		permissaoRepository.salvar(permissao);
		
		for (Permissao permissao2 : permissaoRepository.listar()) {
			System.out.println(permissao2);
		}
	}

}
