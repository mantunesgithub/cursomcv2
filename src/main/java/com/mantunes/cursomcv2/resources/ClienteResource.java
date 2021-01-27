package com.mantunes.cursomcv2.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	public	ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
