package br.com.zup.mercadolivre.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.config.validacao.ValidaSeTemIdValue;
import br.com.zup.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercadolivre.model.Produto;

public class NovaCompraRequestDTO {
	
	@Positive
	@NotNull
	private int quantidade;
	
	@NotNull
	@ValidaSeTemIdValue(domaiClass = Produto.class, fieldName = "id", message = "Não foi encontrado um produto com este ID")
	private Long produto;
	
	@NotNull
	private GatewayPagamento meioDePagamento;

	public NovaCompraRequestDTO(int quantidade, Long produto, GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
		this.meioDePagamento = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getProduto() {
		return produto;
	}

	public GatewayPagamento getMeioDePagamento() {
		return meioDePagamento;
	}
	
	
}
