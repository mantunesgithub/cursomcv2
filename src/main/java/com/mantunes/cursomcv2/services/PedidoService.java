package com.mantunes.cursomcv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.domain.Pedido;
import com.mantunes.cursomcv2.repositories.PedidoRepository;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class PedidoService {
	@Autowired
	private	PedidoRepository	repo;
	
	public	Pedido buscar(Integer id) throws Throwable {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Pedido.class.getName()));
	}
}
