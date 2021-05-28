package br.com.zup.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.controller.dto.NovasImagensRequest;
import br.com.zup.mercadolivre.controller.dto.ProdutoRequestDto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	public ResponseEntity<?> cadastrarProduto(@AuthenticationPrincipal Usuario usuario,  @RequestBody @Valid ProdutoRequestDto produtoRequestDto){
		Produto produto = produtoRequestDto.converter(manager, usuario);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public void adicionaImagensProduto(@PathVariable("id") Long id, @Valid NovasImagensRequest request, @AuthenticationPrincipal Usuario usuario) {
		
		Produto produto = manager.find(Produto.class, id);
		Usuario possivelDonoProduto = manager.find(Usuario.class,usuario.getId());
		System.out.println(possivelDonoProduto);
		
		if(!produto.pertenceAoUsuario(possivelDonoProduto)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);
		manager.merge(produto);
	}
}
