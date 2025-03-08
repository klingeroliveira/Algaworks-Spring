package com.algaworks.algafood.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelPrioridadeNotificador;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoNotificador;

@Component
public class AtivacaoClienteService {

	/*@Autowired
	 * Utilizando um List para injetar todos os notificadores
	private List<Notificador> notificadores;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		for (Notificador notificador : notificadores) {		
			notificador.notificar(cliente, "Seu cadastro no sistema foi ativado.");
		}
		
	}*/
	
	/*
	//utilizando @Primary no bean
	@Autowired
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();		
				
		notificador.notificar(cliente, "Seu cadastro no sistema foi ativado.");
		
	}*/
	
	//@Qualifier("urgente") //definindo a prioridade por qualificação
//	@TipoNotificador(NivelPrioridadeNotificador.URGENTE) //qualifier customizado
//	@Autowired
//	private Notificador notificador;
	
	//ApplicationEventPublisher interface injetada para publicação de eventos
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();		
			
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		//notificador.notificar(cliente, "Seu cadastro no sistema foi ativado.");
		
	}

}
