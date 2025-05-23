package com.algaworks.algafood.di.notificacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier //qualifier customizado
public @interface TipoNotificador {
	
	NivelPrioridadeNotificador value();
}
