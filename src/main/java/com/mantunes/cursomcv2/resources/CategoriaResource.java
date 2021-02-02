package com.mantunes.cursomcv2.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mantunes.cursomcv2.DTO.CategoriaDTO;
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
	
	public	ResponseEntity<Categoria> find(@PathVariable Integer id) throws Throwable {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	/*
	 *	Sessão 3: Operações de CRUD e Casos de uso 
	 *	Inserir/Update/Delete / findAll(todas)  / uma Categoria via Postman
	 */
	@RequestMapping(method=RequestMethod.POST)
	public	ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return	ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public	ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) throws Throwable {
		obj.setId(id);
		obj = service.update(obj);
		return	ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public	ResponseEntity<Void> delete(@PathVariable Integer id) throws Throwable  {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	/*
	 * Se fosse trazer para cada Categoria todos os produtos seria assim. Mas queremos somente
	 * as Categoria, então vamos usar o DTO para trafegar somente os dados que queremos
	 */
//	@RequestMapping(method=RequestMethod.GET)
//	public	ResponseEntity<List <Categoria>> findAll() throws Throwable {
//		List <Categoria> list = service.findAll();
//		return ResponseEntity.ok().body(list);
//	}
	@RequestMapping(method=RequestMethod.GET)
	public	ResponseEntity<List <CategoriaDTO>> findAll() throws Throwable {
		
		List <Categoria> list = service.findAll();
		
		List <CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}

}
