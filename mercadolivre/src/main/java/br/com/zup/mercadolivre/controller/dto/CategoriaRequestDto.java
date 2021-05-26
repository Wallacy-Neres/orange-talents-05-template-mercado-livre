package br.com.zup.mercadolivre.controller.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.config.validacao.UniqueValue;
import br.com.zup.mercadolivre.config.validacao.ValidaSeTemIdCategoriaValue;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

public class CategoriaRequestDto {

	@NotNull
	@NotBlank
	@UniqueValue(domaiClass = Categoria.class, fieldName = "nome", message = "Já Existe uma categoria cadastrada com este nome")
	private String nome;
	
	@ValidaSeTemIdCategoriaValue(domaiClass = Categoria.class, fieldName = "id")
	private Long categoriaMaeId;
	
	public String getNome() {
		return nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public Categoria converter(CategoriaRepository categoriaRepository) {
		Optional<Long> possivelCategoriaMaeId = Optional.ofNullable(categoriaMaeId);
		Categoria categoriaMae = null;
		if(possivelCategoriaMaeId.isPresent()) {
			categoriaMae = categoriaRepository.findById(categoriaMaeId).get();
		}
		return new Categoria(nome, categoriaMae);
	}

}
