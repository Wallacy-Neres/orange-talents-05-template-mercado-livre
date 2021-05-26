package br.com.zup.mercadolivre.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.mercadolivre.config.validacao.UniqueValue;
import br.com.zup.mercadolivre.model.Usuario;

public class UsuarioRequestDto {
	
	@UniqueValue(domaiClass = Usuario.class, fieldName = "login", message = "Já existe um usuario cadastrado com este email!")
	@NotNull
	@NotBlank
	@Email
	private String login;
	
	@NotNull
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario converter() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaHash = encoder.encode(senha);
		return new Usuario(login, senhaHash);
	}
	
	
}
