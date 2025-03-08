package com.algaworks.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.notificacao.NivelPrioridadeNotificador;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoNotificador;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

//classe que executa uma operação a partir de um evento
@Component
public class NotificacaoService {
	
	//@Qualifier("urgente") //definindo a prioridade por qualificação
	@TipoNotificador(NivelPrioridadeNotificador.URGENTE) //qualifier customizado
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema foi ativado.");
	}

}
