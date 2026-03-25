package br.com.autoStock.api.autoStock_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MovimentacaoDTO> listar() {
        return movimentacaoRepository.findAllByOrderByDataDesc()
                .stream()
                .map(MovimentacaoDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public List<MovimentacaoDTO> listarPorProduto(@PathVariable Long id) {
        return movimentacaoRepository.findByProdutoIdOrderByDataDesc(id)
                .stream()
                .map(MovimentacaoDTO::fromEntity)
                .toList();
    }
}
