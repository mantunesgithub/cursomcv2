package com.mantunes.cursomcv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.domain.Categoria;
import com.mantunes.cursomcv2.repositories.CategoriaRepository;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class CategoriaService {
	@Autowired
	private	CategoriaRepository	repo;
	
	public	Categoria find(Integer id) throws Throwable {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Categoria.class.getName()));
	}
	/*
	 *	Sessão 3: Operações de CRUD e Casos de uso 
	 *	Insert uma Categoria via Postman - Qdi ID value null ele inseri senão atza
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);	//Para garantir que o metodo save use como inserção e nao atzcao
		return repo.save(obj);
	}
	/*
	 *	Sessão 3: Operações de CRUD e Casos de uso 
	 *	Update uma Categoria via Postman
	 */
	public Categoria update(Categoria obj) throws Throwable {
		find(obj.getId());				//checar se existe o id
		return repo.save(obj);
	}
}
