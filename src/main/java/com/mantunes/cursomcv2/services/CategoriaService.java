package com.mantunes.cursomcv2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.domain.Categoria;
import com.mantunes.cursomcv2.repositories.CategoriaRepository;
import com.mantunes.cursomcv2.services.exceptions.DataIntegrityException;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

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
	 *	Inserir/Update/Delete uma Categoria via Postman
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);	//Para garantir que o metodo save use como inserção e nao atzcao
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) throws Throwable {
		find(obj.getId());				//checar se existe o id
		return repo.save(obj);
	}
	public void delete(Integer id) throws Throwable {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw	new DataIntegrityException("Não é possivel excluir uma Catgoria que"
					+ " contem produtos"); 
		}
	}
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	/*
	 * Método para paginação de Categoria informando parametros ou assumindo o default 
	 */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return	repo.findAll(pageRequest);
	}
}
