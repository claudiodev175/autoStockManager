package br.com.autoStock.api.autoStock_api.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoStock.api.autoStock_api.model.Movimentacao;
import org.springframework.data.domain.Pageable;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
     Page<Movimentacao> findByProdutoId(Long produtoId, Pageable pageable);
}