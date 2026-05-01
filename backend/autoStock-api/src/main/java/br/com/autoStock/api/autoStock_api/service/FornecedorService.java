package br.com.autoStock.api.autoStock_api.service;

import br.com.autoStock.api.autoStock_api.dto.FornecedorRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.FornecedorResponseDTO;
import br.com.autoStock.api.autoStock_api.exceptions.ProdutoNaoEncontradoException;
import br.com.autoStock.api.autoStock_api.model.Fornecedor;
import br.com.autoStock.api.autoStock_api.repository.FornecedorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // ✅ LISTAR TODOS (PAGINADO)
    public Page<FornecedorResponseDTO> listarTodos(Pageable pageable) {
        return fornecedorRepository.findAll(pageable)
                .map(FornecedorResponseDTO::fromEntity);
    }

    // ✅ LISTAR APENAS ATIVOS
    public Page<FornecedorResponseDTO> listarAtivos(Pageable pageable) {
        return fornecedorRepository.findByAtivoTrue(pageable)
                .map(FornecedorResponseDTO::fromEntity);
    }

    // ✅ BUSCAR POR ID
    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    // ✅ CRIAR NOVO FORNECEDOR
    @Transactional
    public FornecedorResponseDTO criar(FornecedorRequestDTO request) {
        // Verificar se CNPJ já existe
        if (fornecedorRepository.findByCnpj(request.getCnpj()).isPresent()) {
            throw new RuntimeException("CNPJ já cadastrado: " + request.getCnpj());
        }

        Fornecedor fornecedor = Fornecedor.builder()
                .nome(request.getNome())
                .cnpj(request.getCnpj())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .endereco(request.getEndereco())
                .contatoNome(request.getContatoNome())
                .prazoEntrega(request.getPrazoEntrega())
                .condicaoPagamento(request.getCondicaoPagamento())
                .ativo(true)
                .build();

        return FornecedorResponseDTO.fromEntity(fornecedorRepository.save(fornecedor));
    }

    // ✅ ATUALIZAR FORNECEDOR
    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO request) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));

        // Verificar se CNPJ já existe (se foi alterado)
        if (!fornecedor.getCnpj().equals(request.getCnpj())) {
            if (fornecedorRepository.findByCnpj(request.getCnpj()).isPresent()) {
                throw new RuntimeException("CNPJ já cadastrado: " + request.getCnpj());
            }
        }

        fornecedor.setNome(request.getNome());
        fornecedor.setCnpj(request.getCnpj());
        fornecedor.setEmail(request.getEmail());
        fornecedor.setTelefone(request.getTelefone());
        fornecedor.setEndereco(request.getEndereco());
        fornecedor.setContatoNome(request.getContatoNome());
        fornecedor.setPrazoEntrega(request.getPrazoEntrega());
        fornecedor.setCondicaoPagamento(request.getCondicaoPagamento());

        return FornecedorResponseDTO.fromEntity(fornecedorRepository.save(fornecedor));
    }

    // ✅ DELETAR (DELETE FÍSICO)
    @Transactional
    public void deletar(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
        fornecedorRepository.delete(fornecedor);
    }

    // ✅ DESATIVAR (DELETE LÓGICO - recomendado)
    @Transactional
    public void desativar(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }

    // ✅ ATIVAR
    @Transactional
    public void ativar(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Fornecedor não encontrado com ID: " + id));
        fornecedor.setAtivo(true);
        fornecedorRepository.save(fornecedor);
    }

    // ✅ BUSCAR POR NOME (pesquisa)
    public Page<FornecedorResponseDTO> buscarPorNome(String nome, Pageable pageable) {
        return fornecedorRepository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(FornecedorResponseDTO::fromEntity);
    }
}