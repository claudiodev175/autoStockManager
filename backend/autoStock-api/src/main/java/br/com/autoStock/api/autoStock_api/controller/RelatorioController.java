package br.com.autoStock.api.autoStock_api.controller;

import br.com.autoStock.api.autoStock_api.dto.*;
import br.com.autoStock.api.autoStock_api.service.RelatorioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // 1. PRODUTOS COM ESTOQUE BAIXO
    @GetMapping("/estoque-baixo")
    public ResponseEntity<Page<RelatorioEstoqueBaixoDTO>> estoqueBaixo(
            @RequestParam(defaultValue = "10") Integer limite,
            @PageableDefault(size = 20, sort = "quantidade", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(relatorioService.estoqueBaixo(limite, pageable));
    }

    // 2. VALOR TOTAL DO ESTOQUE
    @GetMapping("/valor-total")
    public ResponseEntity<RelatorioValorTotalDTO> valorTotalEstoque() {
        return ResponseEntity.ok(relatorioService.valorTotalEstoque());
    }

    // 3. PRODUTOS SEM MOVIMENTAÇÃO (últimos N dias)
    @GetMapping("/sem-movimentacao")
    public ResponseEntity<Page<RelatorioEstoqueBaixoDTO>> produtosSemMovimentacao(
            @RequestParam(defaultValue = "30") Integer dias,
            @PageableDefault(size = 20, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(relatorioService.produtosSemMovimentacao(dias, pageable));
    }

    // 4. ENTRADAS POR PERÍODO
    @GetMapping("/entradas")
    public ResponseEntity<Long> entradasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(relatorioService.entradasPorPeriodo(dataInicio, dataFim));
    }

    // 5. SAÍDAS POR PERÍODO
    @GetMapping("/saidas")
    public ResponseEntity<Long> saidasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(relatorioService.saidasPorPeriodo(dataInicio, dataFim));
    }

    // 6. PRODUTOS MAIS VENDIDOS (TOP)
    @GetMapping("/mais-vendidos")
    public ResponseEntity<Page<RelatorioProdutoMaisVendidoDTO>> produtosMaisVendidos(
            @PageableDefault(size = 10, sort = "totalQuantidadeSaida", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(relatorioService.produtosMaisVendidos(pageable));
    }
}