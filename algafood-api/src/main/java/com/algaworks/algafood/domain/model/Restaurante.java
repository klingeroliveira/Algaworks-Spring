package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column (length = 30, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Double taxaFrete;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cozinha cozinha;

}
