package br.com.zup.mercadolivre.controller.dto;

public class TokenResponseDto {

	private String token;
	private String tipo;

	public TokenResponseDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
}
