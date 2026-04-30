package br.com.autoStock.api.autoStock_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import br.com.autoStock.api.autoStock_api.dto.MovimentacaoRequestDTO;
import br.com.autoStock.api.autoStock_api.service.MovimentacaoService;

import br.com.autoStock.api.autoStock_api.dto.MovimentacaoDTO;
import br.com.autoStock.api.autoStock_api.repository.MovimentacaoRepository;
import br.com.autoStock.api.autoStock_api.model.Movimentacao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoRepository movimentacaoRepository;
     private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoRepository movimentacaoRepository, MovimentacaoService movimentacaoService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoService = movimentacaoService;
    }

   // ✅ LISTAR TODAS MOVIMENTAÇÕES (PAGINADO)
    @GetMapping
    public Page<MovimentacaoDTO> listar(Pageable pageable) {
        return movimentacaoRepository
                .findAll(pageable)
                .map(MovimentacaoDTO::fromEntity);
    }

    // ✅ LISTAR MOVIMENTAÇÕES POR PRODUTO
    @GetMapping("/produto/{id}")
    public Page<MovimentacaoDTO> listarPorProduto(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return movimentacaoRepository
                .findByProdutoId(id, pageable)
                .map(MovimentacaoDTO::fromEntity);
    }

    // ✅ REGISTRAR ENTRADA (COMPRA)
    @PostMapping("/entrada")
    public ResponseEntity<Movimentacao> registrarEntrada(@RequestBody MovimentacaoRequestDTO request) {
        Movimentacao movimentacao = movimentacaoService.registrarEntrada(request);
        return ResponseEntity.ok(movimentacao);
    }

    // ✅ REGISTRAR SAÍDA (VENDA)
    @PostMapping("/saida")
    public ResponseEntity<Movimentacao> registrarSaida(@RequestBody MovimentacaoRequestDTO request) {
        Movimentacao movimentacao = movimentacaoService.registrarSaida(request);
        return ResponseEntity.ok(movimentacao);
    }
}
