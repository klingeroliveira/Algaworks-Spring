package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Primary //definindo como prioritaria
//@Qualifier("normal") //definindo a prioridade por qualificação
@TipoNotificador(NivelPrioridadeNotificador.URGENTE) //qualifier customizado
//@Profile("prod")
@Component
public class NotificadorEmail implements Notificador {
	
	/*
	 * @Value("${notificador.email.host-servidor}") private String host;
	 * 
	 * @Value("${notificador.email.port-servidor}") private Integer port;
	 */
	@Autowired
	private NotificadorProperties notificadorProperties;
	
	public NotificadorEmail() {
		System.out.println("NotificadorEmail Prod");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("host: " + notificadorProperties.getHostServidor());
		System.out.println("port: " + notificadorProperties.getPortServidor());
		
		System.out.printf("Noticando cliente %s através do email %s, mensagem: %s \n", cliente.getNome(),
				cliente.getEmail(), mensagem);
	}

}
