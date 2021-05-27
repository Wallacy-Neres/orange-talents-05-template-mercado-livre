package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.dto.ProdutoRequestDto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto produtoRequestDto){
		Produto produto = produtoRequestDto.converter(manager);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}
