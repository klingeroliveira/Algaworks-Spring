package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Qualifier("urgente") //definindo a prioridade por qualificação
@TipoNotificador(NivelPrioridadeNotificador.EMERGENTE) //qualifier customizado
@Component
public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Noticando cliente %s por SMS %s, mensagem: %s \n",
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}
