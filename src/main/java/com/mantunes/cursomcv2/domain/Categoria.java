package com.mantunes.cursomcv2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria implements Serializable  {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer	id;
	private	String	nome;

	//do lado que voce quer que venha os obj associados e do outro lado (produto) backreference
	//depois professor pediu pra tirar @JsonManagedReference e onde esta @JsonBackReference colocar
	//JsonIgnore porque testes de problema e dessa forma funciona tambem
	
//	@JsonManagedReference
	@ManyToMany (mappedBy = "categorias")
	private List <Produto> produtos = new ArrayList<>();
	
	public Categoria() {
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	public List <Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List <Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
