package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class AlteracaoFormaPagamentoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		for (FormaPagamento formaPagamento2 : formaPagamentoRepository.listar()) {
			System.out.println(formaPagamento2);
		}
		
		FormaPagamento formaPagamento = formaPagamentoRepository.buscar(2L);
		
		formaPagamento.setDescricao("Cartão Débito 2");
		formaPagamentoRepository.salvar(formaPagamento);
		
		for (FormaPagamento formaPagamento2 : formaPagamentoRepository.listar()) {
			System.out.println(formaPagamento2);
		}

	}

}
