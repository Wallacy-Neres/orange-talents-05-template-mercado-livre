package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.controller.dto.NovaCompraRequestDTO;
import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
@RequestMapping("compra")
public class FechaCompraParte1Controller {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarCompra(@AuthenticationPrincipal Usuario usuario, 
			@RequestBody @Valid NovaCompraRequestDTO request, UriComponentsBuilder builder) throws BindException{
		Long produtoSelecionado = request.getProduto();
		Produto produtoAserComprado = manager.find(Produto.class, produtoSelecionado);
		int quatidade = request.getQuantidade();
		boolean abatido = produtoAserComprado.abateEstoque(quatidade);
		
		
		if(abatido) {
			GatewayPagamento gateway = request.getMeioDePagamento();
			Compra novaCompra = new Compra(produtoAserComprado, quatidade, usuario,gateway);
			manager.persist(novaCompra);
			
			if(gateway.equals(GatewayPagamento.pagseguro)) {
				UriComponents urlRetornoPagseguro = builder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId().toString());
				return ResponseEntity.status(HttpStatus.FOUND).body("pagseguro.com/" + novaCompra.getId() + "?redictUrl="+urlRetornoPagseguro);
			}else {
				UriComponents urlRetornoPaypal = builder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId().toString());
				return ResponseEntity.status(HttpStatus.FOUND).body("paypal.com/" + novaCompra.getId() + "?redictUrl="+urlRetornoPaypal);
			}
		}
		
		BindException problemaEstoque = new BindException(request, "novaCompraRequestDTO");
		problemaEstoque.reject(null, "problema com estoque");
		throw problemaEstoque;
	}
}
