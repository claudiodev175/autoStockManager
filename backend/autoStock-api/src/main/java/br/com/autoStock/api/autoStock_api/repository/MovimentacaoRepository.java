package br.com.autoStock.api.autoStock_api.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoStock.api.autoStock_api.model.Movimentacao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
     Page<Movimentacao> findByProdutoId(Long produtoId, Pageable pageable);

      // ✅ Total de entrada por período
    @Query("SELECT COALESCE(SUM(m.quantidade), 0) FROM Movimentacao m " +
           "WHERE m.tipo = 'ENTRADA' AND m.data BETWEEN :inicio AND :fim")
    Long sumQuantidadeEntradaByPeriodo(@Param("inicio") LocalDateTime inicio, 
                                        @Param("fim") LocalDateTime fim);
    
    // ✅ Total de saída por período
    @Query("SELECT COALESCE(SUM(m.quantidade), 0) FROM Movimentacao m " +
           "WHERE m.tipo = 'SAIDA' AND m.data BETWEEN :inicio AND :fim")
    Long sumQuantidadeSaidaByPeriodo(@Param("inicio") LocalDateTime inicio, 
                                      @Param("fim") LocalDateTime fim);
    
    // ✅ Produtos mais vendidos (Top N)
    @Query("SELECT m.produto.id as produtoId, m.produto.nome as produtoNome, m.produto.sku as sku, " +
           "SUM(m.quantidade) as totalQuantidadeSaida " +
           "FROM Movimentacao m WHERE m.tipo = 'SAIDA' " +
           "GROUP BY m.produto.id, m.produto.nome, m.produto.sku " +
           "ORDER BY totalQuantidadeSaida DESC")
    Page<Object[]> findTopProdutosMaisVendidos(Pageable pageable);
}
