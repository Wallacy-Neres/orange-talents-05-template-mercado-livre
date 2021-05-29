package br.com.zup.mercadolivre.email;

import java.util.List;

public class Mensagem {
	
	private String remetente;
	
	private String destinatarios;
	
	private String assunto;
	
	private String corpo;

	public Mensagem(String remetente, String destinatarios, String assunto, String corpo) {
		super();
		this.remetente = remetente;
		this.destinatarios = destinatarios;
		this.assunto = assunto;
		this.corpo = corpo;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	
}
