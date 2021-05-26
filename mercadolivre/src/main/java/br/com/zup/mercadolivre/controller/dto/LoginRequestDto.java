package br.com.zup.mercadolivre.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequestDto {
	
	private String login;
	private String senha;
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
