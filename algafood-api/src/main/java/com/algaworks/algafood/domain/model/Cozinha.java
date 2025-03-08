package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * essa anotação contém @ToString, @EqualsAndHashCode, @Getterem e @Setter
 * */
@Data 

/**
 * cria equals e hashcode apenas nos atributos que eu indicar
 * */
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@Entity
public class Cozinha {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 30, nullable = false)
	private String nome;	

}
