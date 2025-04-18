package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}	

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	public List<Restaurante> buscarPorCozinha(Long id) {
		return manager.createQuery("from Restaurante where cozinha_id = :cozinhaId").setParameter("cozinhaId", id).getResultList();
	}
	
	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {		
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Restaurante restaurante = buscar(id);
		
		if(restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(restaurante);
	}

}
