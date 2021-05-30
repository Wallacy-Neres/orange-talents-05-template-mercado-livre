package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.mercadolivre.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
