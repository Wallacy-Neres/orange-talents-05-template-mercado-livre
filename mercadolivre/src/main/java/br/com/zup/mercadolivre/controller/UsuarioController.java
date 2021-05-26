package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.dto.UsuarioRequestDto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
		Usuario usuario = usuarioRequestDto.converter();
		usuarioRepository.save(usuario);
		return null;
	}
}
