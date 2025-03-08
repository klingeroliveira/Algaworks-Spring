package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;

//classe utilizada na publicação de um evento
public class ClienteAtivadoEvent {
	
	private Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}	

}
