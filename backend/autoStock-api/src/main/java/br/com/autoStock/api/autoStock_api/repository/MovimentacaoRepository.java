package br.com.autoStock.api.autoStock_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoStock.api.autoStock_api.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByProdutoIdOrderByDataDesc(Long produtoId);

    List<Movimentacao> findAllByOrderByDataDesc();
}