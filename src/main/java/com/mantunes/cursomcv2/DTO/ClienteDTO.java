package com.mantunes.cursomcv2.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private	Integer	id;
	@NotEmpty(message="Este campo deve ser preenchido")
	@Length(min=5, max=90, message="O Tamanho deve ser entre 5 e 90 chars")
	private String	nome;
	@NotEmpty(message="Este campo deve ser preenchido")
	@Email(message="Email inválido")
	private	String 	email;
	
	public ClienteDTO() {
		super();
	}
	public ClienteDTO (Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
