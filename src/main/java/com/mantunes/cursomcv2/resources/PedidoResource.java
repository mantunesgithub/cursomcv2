package com.mantunes.cursomcv2.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mantunes.cursomcv2.domain.Pedido;
import com.mantunes.cursomcv2.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos")

public class PedidoResource {
	//Controlador rest contem metodos pequenos então para capturar a excessão
	//vamos cirar um obj especial handler para interceptar e lançar rsposta http adequada
	
	@Autowired
	private PedidoService  service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	
	public	ResponseEntity<Pedido> find(@PathVariable Integer id) throws Throwable {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
