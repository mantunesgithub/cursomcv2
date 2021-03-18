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
//Para testar envio de Email abrir o banco de dados do Mysql c\:xamp > xampp-controler ? Star no Mysql
//Para ver o phpadmin  .Digite na url => localhost:
//
@SpringBootApplication
public class CursomcV2Application implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(CursomcV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
