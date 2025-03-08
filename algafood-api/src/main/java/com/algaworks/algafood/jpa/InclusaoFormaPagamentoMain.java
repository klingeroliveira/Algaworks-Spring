package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class InclusaoFormaPagamentoMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao("Dinheiro");
		
		formaPagamentoRepository.salvar(formaPagamento);
		
		for (FormaPagamento formaPagamento2 : formaPagamentoRepository.listar()) {
			System.out.println(formaPagamento2);
		}
	}
}