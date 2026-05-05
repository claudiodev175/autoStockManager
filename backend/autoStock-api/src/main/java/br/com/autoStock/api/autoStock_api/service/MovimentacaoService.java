package br.com.autoStock.api.autoStock_api.service;

import br.com.autoStock.api.autoStock_api.dto.MovimentacaoRequestDTO;
import br.com.autoStock.api.autoStock_api.enums.TipoMovimentacao;
import br.com.autoStock.api.autoStock_api.exceptions.EstoqueInsuficienteException;
import br.com.autoStock.api.autoStock_api.exceptions.ProdutoNaoEncontradoException;
import br.com.autoStock.api.autoStock_api.model.Movimentacao;
import br.com.autoStock.api.autoStock_api.model.Produto;
import br.com.autoStock.api.autoStock_api.repository.MovimentacaoRepository;
import br.com.autoStock.api.autoStock_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, 
                               ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * ENTRADA DE PRODUTOS (aumenta o estoque)
     */
    @Transactional
    public Movimentacao registrarEntrada(MovimentacaoRequestDTO request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));

        // Aumenta o estoque
        produto.setQuantidade(produto.getQuantidade() + request.getQuantidade());
        produtoRepository.save(produto);

        // Registra movimentação
        Movimentacao movimentacao = Movimentacao.builder()
                .produto(produto)
                .tipo(TipoMovimentacao.ENTRADA)
                .quantidade(request.getQuantidade())
                .data(LocalDateTime.now())
                .build();

        return movimentacaoRepository.save(movimentacao);
    }

    /**
     * SAÍDA DE PRODUTOS (diminui o estoque)
     */
    @Transactional
    public Movimentacao registrarSaida(MovimentacaoRequestDTO request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));

        // Verifica se tem estoque suficiente
        if (produto.getQuantidade() < request.getQuantidade()) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente! Disponível: " + produto.getQuantidade() + 
                ", Solicitado: " + request.getQuantidade()
            );
        }

        // Diminui o estoque
        produto.setQuantidade(produto.getQuantidade() - request.getQuantidade());
        produtoRepository.save(produto);

        // Registra movimentação
        Movimentacao movimentacao = Movimentacao.builder()
                .produto(produto)
                .tipo(TipoMovimentacao.SAIDA)
                .quantidade(request.getQuantidade())
                .data(LocalDateTime.now())
                .build();

        return movimentacaoRepository.save(movimentacao);
    }
}