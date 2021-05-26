package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Email
	@NotNull
	@NotBlank
	private String login;
	
	@NotNull
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	@Deprecated
	public Usuario() {
		
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

}
