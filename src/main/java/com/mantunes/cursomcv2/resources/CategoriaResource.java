package com.mantunes.cursomcv2.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public	ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return	ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public	ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) throws Throwable {
		Categoria obj = service.fromDTO(objDTO);
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
	 * as Categoria, então vamos usar o DTO para trafegar somente os dados que queremos que são as Categorias
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
	/*
	 * Método para paginação de Categoria informando parametros ou assumindo o default 
	 * http://localhost:8081/categorias/page
	 * http://localhost:8081/categorias/page?linesPerPage=3
	 * http://localhost:8081/categorias/page?linesPerPage=3&page=1
	 * http://localhost:8081/categorias/page?linesPerPage=3&page=1&direction=DESC
	 */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public	ResponseEntity<Page <CategoriaDTO>> findPage(
			@RequestParam (value="page", defaultValue = "0") Integer page,
			@RequestParam (value="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam (value="orderBy", defaultValue = "id")String orderBy,
			@RequestParam (value="direction", defaultValue = "ASC")String direction){
		
		Page<Categoria> list = service.findPage(page,linesPerPage,orderBy,direction );
		
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
