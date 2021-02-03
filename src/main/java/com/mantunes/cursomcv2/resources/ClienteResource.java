package com.mantunes.cursomcv2.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mantunes.cursomcv2.DTO.ClienteDTO;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")

public class ClienteResource {
	//Controlador rest contem metodos pequenos então para capturar a excessão
	//vamos cirar um obj especial handler para interceptar e lançar rsposta http adequada
	@Autowired
	private ClienteService  service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	
	public	ResponseEntity<Cliente> find(@PathVariable Integer id) throws Throwable {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public	ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) throws Throwable {
		Cliente obj = service.fromDTO(objDTO);
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
	 * Se fosse trazer para cada Cliente todos os produtos seria assim. Mas queremos somente
	 * as Cliente, então vamos usar o DTO para trafegar somente os dados que queremos que são as Clientes
	 */
//	@RequestMapping(method=RequestMethod.GET)
//	public	ResponseEntity<List <Cliente>> findAll() throws Throwable {
//		List <Cliente> list = service.findAll();
//		return ResponseEntity.ok().body(list);
//	}
	@RequestMapping(method=RequestMethod.GET)
	public	ResponseEntity<List <ClienteDTO>> findAll() throws Throwable {
		
		List <Cliente> list = service.findAll();
		
		List <ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	/*
	 * Método para paginação de Cliente informando parametros ou assumindo o default 
	 * http://localhost:8081/categorias/page
	 * http://localhost:8081/categorias/page?linesPerPage=3
	 * http://localhost:8081/categorias/page?linesPerPage=3&page=1
	 * http://localhost:8081/categorias/page?linesPerPage=3&page=1&direction=DESC
	 */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public	ResponseEntity<Page <ClienteDTO>> findPage(
			@RequestParam (value="page", defaultValue = "0") Integer page,
			@RequestParam (value="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam (value="orderBy", defaultValue = "nome")String orderBy,
			@RequestParam (value="direction", defaultValue = "ASC")String direction){
		
		Page<Cliente> list = service.findPage(page,linesPerPage,orderBy,direction );
		
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
