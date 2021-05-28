package br.com.zup.mercadolivre.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class uploaderFake {

	public static Set<String> envia(List<MultipartFile> imagens){
		String url = "http://neres/produto/";
		return imagens.stream()
			.map(imagem -> url + imagem.getOriginalFilename()).collect(Collectors.toSet());
	}
}
