package com.mantunes.cursomcv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class ClienteService {
	@Autowired
	private	ClienteRepository	repo;
	
	public	Cliente buscar(Integer id) throws Throwable {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Cliente.class.getName()));
	}
}
