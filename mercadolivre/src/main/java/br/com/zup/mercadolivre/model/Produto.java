package br.com.zup.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.controller.dto.NovaCaracteristicaRequest;
import br.com.zup.mercadolivre.controller.dto.OpinioesResponseDTO;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	@JoinColumn(name = "id_dono_produto")
	@ManyToOne
	private Usuario donoDoProduto;
	
	@NotBlank
	@Length(max = 1000)
	private String descriçao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	
	@OneToMany(mappedBy = "produto")
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@Deprecated
	public Produto() {
		
	}
	
	public Produto(String nome, BigDecimal valor, Integer quantidade,
			String descricao, Categoria categoria, @Size(min = 3) 
			@Valid Collection<NovaCaracteristicaRequest> caracteristicas, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.descriçao = descricao;
		this.donoDoProduto = usuario;
		Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas
				.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
		.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDonoDoProduto) {
		return this.donoDoProduto.equals(possivelDonoDoProduto);
	}

	public Usuario getDonoDoProduto() {
		return donoDoProduto;
	}

	public void setDonoDoProduto(Usuario donoDoProduto) {
		this.donoDoProduto = donoDoProduto;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescriçao() {
		return descriçao;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public SortedSet<Pergunta> getPerguntas() {
		return perguntas;
	}

	public Set<Opiniao> getOpiniao() {
		return opinioes;
	}

	public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapea) {
		return this.caracteristicas.stream().map(funcaoMapea).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeaImagens(Function<ImagemProduto, T> funcaoMapea) {
		return this.imagens.stream().map(funcaoMapea).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeaPerguntas(Function<Pergunta, T> funcaoMapea) {
		return this.perguntas.stream().map(funcaoMapea).collect(Collectors.toCollection(TreeSet::new));
	}

	public OpinioesResponseDTO getOpinioes() {
		return new OpinioesResponseDTO(this.opinioes);
	}
	
}
