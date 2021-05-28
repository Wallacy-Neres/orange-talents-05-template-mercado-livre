package br.com.zup.mercadolivre.controller.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.config.validacao.ValidaSeTemIdValue;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class OpiniaoRequestDto {

	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max = 500)
	private String descricao;
	
	@NotNull
	@ValidaSeTemIdValue(domaiClass = Produto.class, fieldName = "id", message = "Não existe um produto cadastrado com este ID")
	private Long idProduto;
	
	public OpiniaoRequestDto (Integer nota, String titulo, String descricao, Long idProduto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idProduto = idProduto;
	}
	
	public Opiniao convert(EntityManager manager,Usuario usuario) {
		Produto produto = manager.find(Produto.class, idProduto);
		return new Opiniao(nota, titulo, descricao, produto, usuario);
	}

}
