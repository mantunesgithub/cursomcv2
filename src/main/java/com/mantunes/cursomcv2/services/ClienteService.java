package com.mantunes.cursomcv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.DTO.ClienteDTO;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.services.exceptions.DataIntegrityException;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	@Autowired
	private	ClienteRepository	repo;
	
	public	Cliente find(Integer id) throws Throwable {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) throws Throwable {
		Cliente newObj = find(obj.getId());				//checar se existe o id
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}

	public void delete(Integer id) throws Throwable {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw	new DataIntegrityException("Não é possivel excluir uma Entidade que"
					+ " contem relacionados"); 
		}
	}
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	/*
	 * Método para paginação de Cliente informando parametros ou assumindo o default 
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return	repo.findAll(pageRequest);
	}
	public	Cliente fromDTO (ClienteDTO objDTO) {
		return	new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
		
	}
}
