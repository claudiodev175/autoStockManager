package br.com.autoStock.api.autoStock_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoStock.api.autoStock_api.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findByQuantidadeLessThan(Integer quantidade, Pageable pageable);

  @Query("SELECT p FROM tb_produtos p WHERE p.id NOT IN " +
           "(SELECT m.produto.id FROM Movimentacao m WHERE m.data >= :data)")
    Page<Produto> findProdutosSemMovimentacao(@Param("data") LocalDateTime data, Pageable pageable);

}
