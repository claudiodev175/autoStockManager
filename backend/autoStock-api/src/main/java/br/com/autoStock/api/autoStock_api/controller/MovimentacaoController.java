package br.com.autoStock.api.autoStock_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.autoStock.api.autoStock_api.dto.MovimentacaoDTO;
import br.com.autoStock.api.autoStock_api.repository.MovimentacaoRepository;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoController(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

      @GetMapping
    public Page<MovimentacaoDTO> listar(Pageable pageable) {
        return movimentacaoRepository
                .findAll(pageable)
                .map(MovimentacaoDTO::fromEntity);
    }

    // 🔥 LISTAR POR PRODUTO (PAGINADO)
    @GetMapping("/{id}")
    public Page<MovimentacaoDTO> listarPorProduto(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return movimentacaoRepository
                .findByProdutoId(id, pageable)
                .map(MovimentacaoDTO::fromEntity);
    }
}
