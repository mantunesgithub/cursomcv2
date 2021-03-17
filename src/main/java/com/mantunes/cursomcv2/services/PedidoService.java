package com.mantunes.cursomcv2.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantunes.cursomcv2.domain.ItemPedido;
import com.mantunes.cursomcv2.domain.PagamentoComBoleto;
import com.mantunes.cursomcv2.domain.Pedido;
import com.mantunes.cursomcv2.domain.enums.EstadoPagamento;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.repositories.ItemPedidoRepository;
import com.mantunes.cursomcv2.repositories.PagamentoRepository;
import com.mantunes.cursomcv2.repositories.PedidoRepository;
import com.mantunes.cursomcv2.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private	PedidoRepository	repo;
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;	
	
	@Autowired
	private ClienteService clienteService;	

	public	Pedido find(Integer id) throws Throwable {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " 
				+ Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) throws Throwable {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}	
}
