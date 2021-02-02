package com.mantunes.cursomcv2.DTO;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mantunes.cursomcv2.domain.Categoria;

public class CategoriaDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer	id;
	
	@NotEmpty(message="Este campo deve ser preenchido")
	@Length(min=5, max=90, message="O Tamanho deve ser entre 5 e 90 chars")
	private	String	nome;

	public CategoriaDTO() {
	}
	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
}
