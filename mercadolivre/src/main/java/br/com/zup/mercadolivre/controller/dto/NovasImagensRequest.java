package br.com.zup.mercadolivre.controller.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensRequest {
	
	@Size(min = 1)
	@NotNull
	List<MultipartFile> imagens = new ArrayList<>();
	
	public List<MultipartFile> getImagens() {
		return imagens;
	}
	
	public NovasImagensRequest(@Size(min = 1) @NotNull List<MultipartFile> imagens) {
		this.imagens = imagens;	
	}
}
