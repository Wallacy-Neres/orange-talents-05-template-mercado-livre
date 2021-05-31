package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive
	@NotNull
	private int quantidade;
	
	@ManyToOne
	@NotNull
	private Produto produtoSelecionado;
	
	@ManyToOne
	@NotNull
	private Usuario comprador;

	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;

	public Compra(@NotNull Produto produtoSelecionado, @Positive int quantidade, @NotNull Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.quantidade = quantidade;
		this.produtoSelecionado = produtoSelecionado;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}
	
	
}
