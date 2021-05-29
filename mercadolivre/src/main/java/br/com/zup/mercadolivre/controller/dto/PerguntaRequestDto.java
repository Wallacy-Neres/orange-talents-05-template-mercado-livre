package br.com.zup.mercadolivre.controller.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class PerguntaRequestDto {
	
	@NotBlank
	private String titulo;
	
	private Long idProduto;
	
	
	public PerguntaRequestDto(String titulo, Long idProduto) {
		this.titulo = titulo;
		this.idProduto = idProduto;
	}


	public Pergunta converter(EntityManager manager, Usuario usuario) {
		Produto produto = manager.find(Produto.class, idProduto);
		return new Pergunta(titulo, produto, usuario);
	}


	public String getTitulo() {
		return titulo;
	}
	
	
	
}
