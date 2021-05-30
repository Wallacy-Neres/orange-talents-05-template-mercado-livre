package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.model.CaracteristicaProduto;

public class DetalheCaracteriscaProdutoDTO {
	
	private String nome;
	private String descricao;

	public DetalheCaracteriscaProdutoDTO(CaracteristicaProduto caracteristica) {
		
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
