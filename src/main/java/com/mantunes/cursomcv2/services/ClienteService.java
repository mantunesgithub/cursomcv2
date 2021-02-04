package com.mantunes.cursomcv2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mantunes.cursomcv2.DTO.ClienteDTO;
import com.mantunes.cursomcv2.DTO.ClienteNewDTO;
import com.mantunes.cursomcv2.domain.Cidade;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.domain.Endereco;
import com.mantunes.cursomcv2.domain.enums.TipoCliente;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.repositories.EnderecoRepository;
import com.mantunes.cursomcv2.services.exceptions.DataIntegrityException;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private	ClienteRepository	repo;
	@Autowired
	private	EnderecoRepository	enderecoRepository;
	

	
	public	Cliente find(Integer id) throws Throwable {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Cliente.class.getName()));
	}
	/*
	 *	Sessão 3: Operações de CRUD e Casos de uso 
	 *	Insert uma Categoria via Postman - Qdi ID value null ele inseri senão atza
	 *	Inserir/Update/Delete uma Categoria via Postman
	 */
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);	//Para garantir que o metodo save use como inserção e nao atzcao
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw	new DataIntegrityException("Não é possivel excluir porque há"
					+ " pedidos relacionados"); 
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
	public	Cliente fromDTO (ClienteNewDTO objDTO) {
		
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(),
							TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(),objDTO.getCep(),
							cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if	(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if	(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
}
