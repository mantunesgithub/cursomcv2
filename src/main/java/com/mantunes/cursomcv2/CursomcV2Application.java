package com.mantunes.cursomcv2;

import java.text.SimpleDateFormat;
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
import com.mantunes.cursomcv2.domain.Pagamento;
import com.mantunes.cursomcv2.domain.PagamentoComBoleto;
import com.mantunes.cursomcv2.domain.PagamentoComCartao;
import com.mantunes.cursomcv2.domain.Pedido;
import com.mantunes.cursomcv2.domain.Produto;
import com.mantunes.cursomcv2.domain.enums.EstadoPagamento;
import com.mantunes.cursomcv2.domain.enums.TipoCliente;
import com.mantunes.cursomcv2.repositories.CategoriaRepository;
import com.mantunes.cursomcv2.repositories.CidadeRepository;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.repositories.EnderecoRepository;
import com.mantunes.cursomcv2.repositories.EstadoRepository;
import com.mantunes.cursomcv2.repositories.PagamentoRepository;
import com.mantunes.cursomcv2.repositories.PedidoRepository;
import com.mantunes.cursomcv2.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcV2Application implements CommandLineRunner  {

	@Autowired
	private	CategoriaRepository categoriaRepository;
	@Autowired
	private	ProdutoRepository 	produtoRepository;
	@Autowired
	private	EstadoRepository 	estadoRepository;
	@Autowired
	private	CidadeRepository 	cidadeRepository;
	@Autowired
	private	ClienteRepository 	clienteRepository;
	@Autowired
	private	EnderecoRepository	enderecoRepository;
	@Autowired
	private	PedidoRepository 	pedidoRepository;
	@Autowired
	private	PagamentoRepository pagamentoRepository;
	
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
//		System.out.println("cliente e endereço repository");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
//		if (cli1 != null && e1 != null ) {
//			System.out.println("chegou Pedido - cli1 = " + cli1 + "e1 = " + e1);
//		}
		System.out.println("sdf=" + sdf);
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
//		System.out.println("Passou Pedido");
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1	= new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6 );
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2	= new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		System.out.println("Passou pedido e pagameto");
	}

	private SimpleDateFormat SimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
