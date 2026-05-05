package br.com.autoStock.api.autoStock_api.service;

import br.com.autoStock.api.autoStock_api.dto.*;
import br.com.autoStock.api.autoStock_api.model.Produto;
import br.com.autoStock.api.autoStock_api.repository.MovimentacaoRepository;
import br.com.autoStock.api.autoStock_api.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public RelatorioService(ProdutoRepository produtoRepository, 
                            MovimentacaoRepository movimentacaoRepository) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    /**
     * 1. RELATÓRIO: PRODUTOS COM ESTOQUE BAIXO
     */
    public Page<RelatorioEstoqueBaixoDTO> estoqueBaixo(Integer limite, Pageable pageable) {
        return produtoRepository.findByQuantidadeLessThan(limite, pageable)
                .map(p -> RelatorioEstoqueBaixoDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .sku(p.getSku())
                        .quantidade(p.getQuantidade())
                        .preco(p.getPreco())
                        .categoria(p.getCategoria())
                        .marca(p.getMarca())
                        .build());
    }

    /**
     * 2. RELATÓRIO: VALOR TOTAL DO ESTOQUE
     */
    public RelatorioValorTotalDTO valorTotalEstoque() {
        java.util.List<Produto> produtos = produtoRepository.findAll();
        
        Integer totalQuantidade = produtos.stream()
                .mapToInt(Produto::getQuantidade)
                .sum();
        
        Double valorTotal = produtos.stream()
                .mapToDouble(p -> p.getQuantidade() * p.getPreco())
                .sum();
        
        return RelatorioValorTotalDTO.builder()
                .totalQuantidade(totalQuantidade)
                .valorTotalEstoque(valorTotal)
                .totalProdutos(produtos.size())
                .build();
    }

    /**
     * 3. RELATÓRIO: PRODUTOS SEM MOVIMENTAÇÃO (últimos N dias)
     */
    public Page<RelatorioEstoqueBaixoDTO> produtosSemMovimentacao(Integer dias, Pageable pageable) {
        LocalDateTime dataCorte = LocalDateTime.now().minusDays(dias);
        return produtoRepository.findProdutosSemMovimentacao(dataCorte, pageable)
                .map(p -> RelatorioEstoqueBaixoDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .sku(p.getSku())
                        .quantidade(p.getQuantidade())
                        .preco(p.getPreco())
                        .categoria(p.getCategoria())
                        .marca(p.getMarca())
                        .build());
    }

    /**
     * 4. RELATÓRIO: ENTRADAS POR PERÍODO
     */
    public Long entradasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(LocalTime.MAX);
        return movimentacaoRepository.sumQuantidadeEntradaByPeriodo(inicio, fim);
    }

    /**
     * 5. RELATÓRIO: SAÍDAS POR PERÍODO
     */
    public Long saidasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(LocalTime.MAX);
        return movimentacaoRepository.sumQuantidadeSaidaByPeriodo(inicio, fim);
    }

    /**
     * 6. RELATÓRIO: PRODUTOS MAIS VENDIDOS
     */
    public Page<RelatorioProdutoMaisVendidoDTO> produtosMaisVendidos(Pageable pageable) {
        return movimentacaoRepository.findTopProdutosMaisVendidos(pageable)
                .map(obj -> RelatorioProdutoMaisVendidoDTO.builder()
                        .produtoId((Long) obj[0])
                        .produtoNome((String) obj[1])
                        .sku((String) obj[2])
                        .totalQuantidadeSaida((Long) obj[3])
                        .totalValorVendido(0.0) // Pode calcular depois se quiser
                        .build());
    }
}