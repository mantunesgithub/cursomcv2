package com.mantunes.cursomcv2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mantunes.cursomcv2.domain.Categoria;
import com.mantunes.cursomcv2.domain.Produto;
import com.mantunes.cursomcv2.repositories.CategoriaRepository;
import com.mantunes.cursomcv2.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcV2Application implements CommandLineRunner  {

	@Autowired
	private	CategoriaRepository categoriaRepository;
	@Autowired
	private	ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "computador" , 2000.00);
		Produto p2 = new Produto(null, "impressora" , 800.00);
		Produto p3 = new Produto(null, "mouse" , 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
