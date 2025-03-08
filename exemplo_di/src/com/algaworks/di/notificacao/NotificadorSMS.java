package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorSMS implements Notificador{
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Noticando cliente %s por SMS através do telefone %s, mensagem: %s \n",
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}
