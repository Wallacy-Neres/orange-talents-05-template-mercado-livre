package br.com.zup.mercadolivre.controller.dto;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.zup.mercadolivre.model.Opiniao;

public class OpinioesResponseDTO {

	private Set<Opiniao> opinioes;

	public OpinioesResponseDTO(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> Set<T> mapeaOpinioes(Function<Opiniao, T> funcaoMapea) {
		return this.opinioes.stream().map(funcaoMapea).collect(Collectors.toSet());
	}

	public double media() {
		
		Set<Integer> notas = mapeaOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElse(0.0);
	}

	public Integer total() {
		return this.opinioes.size();
	}
	
	
}
