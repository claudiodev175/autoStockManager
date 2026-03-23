package br.com.autoStock.api.autoStock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoStock.api.autoStock_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    
}