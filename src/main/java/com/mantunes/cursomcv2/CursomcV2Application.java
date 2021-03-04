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
import com.mantunes.cursomcv2.domain.ItemPedido;
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
import com.mantunes.cursomcv2.repositories.ItemPedidoRepository;
import com.mantunes.cursomcv2.repositories.PagamentoRepository;
import com.mantunes.cursomcv2.repositories.PedidoRepository;
import com.mantunes.cursomcv2.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcV2Application implements CommandLineRunner  {

	//Autowire
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
	@Autowired
	private	ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Hortifrut");
		Categoria cat9 = new Categoria(null, "Farmácia");
		
		Produto p1 = new Produto(null, "computador" , 2000.00);
		Produto p2 = new Produto(null, "impressora" , 800.00);
		Produto p3 = new Produto(null, "mouse" , 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
	
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3,cat4,cat5, cat6, cat7, cat8, cat9));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("sdf=" + sdf);
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1	= new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6 );
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2	= new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);				
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);				
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);	
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

	private SimpleDateFormat SimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
