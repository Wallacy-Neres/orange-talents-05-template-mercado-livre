package br.com.zup.mercadolivre.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.config.validacao.ValidaSeTemIdValue;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;



public class ProdutoRequestDto {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	
	@NotNull
	@ValidaSeTemIdValue(domaiClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public ProdutoRequestDto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@Positive Integer quantidade,@NotBlank @Length(max = 1000) String descricao, 
			@NotNull Long idCategoria, List<NovaCaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas .addAll(caracteristicas);
	}
	
	public String getNome() {
		return nome;
	}

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto converter(EntityManager manager) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome,valor, quantidade, descricao, categoria, caracteristicas);
	}
}
