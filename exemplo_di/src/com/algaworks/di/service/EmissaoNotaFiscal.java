package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.Notificador;

public class EmissaoNotaFiscal {

	private Notificador notificador;	

	public EmissaoNotaFiscal(Notificador notificador) {
		super();
		this.notificador = notificador;
	}

	public void emitirNotaFiscal(Cliente cliente, Produto produto) {

		this.notificador.notificar(cliente, "Nota fiscal emitida para o produto" + produto.getNome());
	}

}
