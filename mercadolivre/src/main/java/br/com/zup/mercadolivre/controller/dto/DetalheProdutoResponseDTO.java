package br.com.zup.mercadolivre.controller.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.mercadolivre.model.ImagemProduto;
import br.com.zup.mercadolivre.model.Produto;

public class DetalheProdutoResponseDTO {

	private String nomeProduto;
	private Set<String> imagensProduto;
	private BigDecimal valorProduto;
	private Set<DetalheCaracteriscaProdutoDTO> caracteristicaProduto;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioesProduto;
	private double mediaNotas;
	private Integer total;

	public DetalheProdutoResponseDTO(Produto produto) {
		this.nomeProduto = produto.getNome();
		this.valorProduto = produto.getValor();
		this.caracteristicaProduto = produto.mapCaracteristicas(DetalheCaracteriscaProdutoDTO :: new);
		this.imagensProduto = produto.mapeaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeaPerguntas(pergunta -> pergunta.getTitulo());
		
		OpinioesResponseDTO  opinioes = produto.getOpinioes();
		this.opinioesProduto = opinioes.mapeaOpinioes(opiniao ->{
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();
		
	}
	
	

	public Set<Map<String, String>> getOpinioesProduto() {
		return opinioesProduto;
	}



	public double getMediaNotas() {
		return mediaNotas;
	}



	public Integer getTotal() {
		return total;
	}



	public String getNomeProduto() {
		return nomeProduto;
	}

	public Set<String> getImagensProduto() {
		return imagensProduto;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}
	
	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<DetalheCaracteriscaProdutoDTO> getCaracteristicaProduto() {
		return caracteristicaProduto;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioesProduto;
	}

}
