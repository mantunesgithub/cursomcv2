package com.mantunes.cursomcv2.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mantunes.cursomcv2.domain.Categoria;
import com.mantunes.cursomcv2.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")

public class CategoriaResource {
	//Controlador rest contem metodos pequenos então para capturar a excessão
	//vamos cirar um obj especial handler para interceptar e lançar rsposta http adequada
	@Autowired
	private CategoriaService  service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	
	public	ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	/*
	 *	Sessão 3: Operações de CRUD e Casos de uso 
	 *	Inserir uma Categoria via Postman
	 */
	
	@RequestMapping(method=RequestMethod.POST)
	public	ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return	ResponseEntity.created(uri).build();
								
	}
	
}
