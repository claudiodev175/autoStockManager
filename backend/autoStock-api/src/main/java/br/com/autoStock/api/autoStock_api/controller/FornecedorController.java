package br.com.autoStock.api.autoStock_api.controller;

import br.com.autoStock.api.autoStock_api.dto.FornecedorRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.FornecedorResponseDTO;
import br.com.autoStock.api.autoStock_api.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // ✅ LISTAR TODOS
    @GetMapping
    public ResponseEntity<Page<FornecedorResponseDTO>> listarTodos(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.listarTodos(pageable));
    }

    // ✅ LISTAR APENAS ATIVOS
    @GetMapping("/ativos")
    public ResponseEntity<Page<FornecedorResponseDTO>> listarAtivos(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.listarAtivos(pageable));
    }

    // ✅ BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }

    // ✅ BUSCAR POR NOME
    @GetMapping("/search")
    public ResponseEntity<Page<FornecedorResponseDTO>> buscarPorNome(
            @RequestParam String nome,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.buscarPorNome(nome, pageable));
    }

    // ✅ CRIAR
    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criar(@Valid @RequestBody FornecedorRequestDTO request) {
        return ResponseEntity.ok(fornecedorService.criar(request));
    }

    // ✅ ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody FornecedorRequestDTO request) {
        return ResponseEntity.ok(fornecedorService.atualizar(id, request));
    }

    // ✅ DELETAR (DELETE FÍSICO)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ DESATIVAR (DELETE LÓGICO)
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        fornecedorService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ ATIVAR
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        fornecedorService.ativar(id);
        return ResponseEntity.noContent().build();
    }
}