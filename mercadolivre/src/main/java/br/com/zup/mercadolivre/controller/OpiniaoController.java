package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.dto.OpiniaoRequestDto;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.OpiniaoRepository;

@RestController
@RequestMapping("opiniao")
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastrarOpiniao(@AuthenticationPrincipal Usuario usuario, 
												@RequestBody @Valid OpiniaoRequestDto opiniaoRequest){
	
		Opiniao opiniao = opiniaoRequest.convert(manager, usuario);
		opiniaoRepository.save(opiniao);
		return ResponseEntity.ok().build();
	}
}
