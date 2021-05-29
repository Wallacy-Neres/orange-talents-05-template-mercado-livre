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

import br.com.zup.mercadolivre.controller.dto.PerguntaRequestDto;
import br.com.zup.mercadolivre.email.EnviaEmail;
import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.PerguntaRepository;


@RestController
@RequestMapping("pergunta")
public class PerguntaController {
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private EnviaEmail enviaEmail;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	public ResponseEntity<?> enviarPergunta(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid PerguntaRequestDto perguntaRequest){
		
		Pergunta pergunta = perguntaRequest.converter(manager, usuario);
		perguntaRepository.save(pergunta);
		
		//enviaEmail.metodoEnviaEmail(pergunta);
		return ResponseEntity.ok().build();
	}
}
