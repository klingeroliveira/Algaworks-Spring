package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.notificacao.NotificadorSMS;
import com.algaworks.di.service.AtivacaoClienteService;

public class Main {
	public static void main(String[] args) {
		Cliente joao = new Cliente("João", "joao@asd.com", "1122223333");
		Cliente maria = new Cliente("Maria", "maria@asd.com", "3344445555");

		AtivacaoClienteService ativador = new AtivacaoClienteService(new NotificadorEmail());
		ativador.ativar(joao);
		ativador.ativar(maria);

		ativador = new AtivacaoClienteService(new NotificadorSMS());
		ativador.ativar(joao);
		ativador.ativar(maria);
	}
}
