package com.mantunes.cursomcv2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mantunes.cursomcv2.domain.Categoria;
import com.mantunes.cursomcv2.domain.Cidade;
import com.mantunes.cursomcv2.domain.Cliente;
import com.mantunes.cursomcv2.domain.Endereco;
import com.mantunes.cursomcv2.domain.Estado;
import com.mantunes.cursomcv2.domain.Produto;
import com.mantunes.cursomcv2.domain.enums.TipoCliente;
import com.mantunes.cursomcv2.repositories.CategoriaRepository;
import com.mantunes.cursomcv2.repositories.CidadeRepository;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.repositories.EnderecoRepository;
import com.mantunes.cursomcv2.repositories.EstadoRepository;
import com.mantunes.cursomcv2.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcV2Application implements CommandLineRunner  {

	@Autowired
	private	CategoriaRepository categoriaRepository;
	@Autowired
	private	ProdutoRepository produtoRepository;
	@Autowired
	private	EstadoRepository estadoRepository;
	@Autowired
	private	CidadeRepository cidadeRepository;
	@Autowired
	private	ClienteRepository clienteRepository;
	@Autowired
	private	EnderecoRepository enderecoRepository;
	
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
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		Cidade c3 = new Cidade(null,"Campinas", est2);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c1 = new Cidade(null, "Uberlandia", est1);

		est1.getCidades().addAll(Arrays.asList(c3));
		est2.getCidades().addAll(Arrays.asList(c1, c2));	
	
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "39777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}
}
