package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary //definindo como prioritaria
//@Qualifier("normal") //definindo a prioridade por qualificação
@TipoNotificador(NivelPrioridadeNotificador.URGENTE) //qualifier customizado
@Profile("dev")
@Component
public class NotificadorEmail_MOCK implements Notificador {
	
	public NotificadorEmail_MOCK() {
		System.out.println("NotificadorEmail MOCK");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK - Exemplo de notificação para cliente %s através do email %s, mensagem: %s \n", cliente.getNome(),
				cliente.getEmail(), mensagem);
	}

}
