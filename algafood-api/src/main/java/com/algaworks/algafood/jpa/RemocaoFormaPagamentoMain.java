package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class RemocaoFormaPagamentoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		for (FormaPagamento formaPagamento2 : formaPagamentoRepository.listar()) {
			System.out.println(formaPagamento2);
		}
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(2L);
		
		System.out.println(formaPagamento);
		
		formaPagamentoRepository.remover(formaPagamento);
		
		for (FormaPagamento formaPagamento2 : formaPagamentoRepository.listar()) {
			System.out.println(formaPagamento2);
		}

	}

}
