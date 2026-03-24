package br.com.autoStock.api.autoStock_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autoStock.api.autoStock_api.model.Produto;
import br.com.autoStock.api.autoStock_api.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto obterProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setSku(produtoAtualizado.getSku());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setMarca(produtoAtualizado.getMarca());
            produto.setModeloVeiculo(produtoAtualizado.getModeloVeiculo());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setPreco(produtoAtualizado.getPreco());
            return produtoRepository.save(produto);
        }).orElse(null);
    }

      public Produto entradaEstoque(Long id, int quantidade) {
        Produto produto = obterProdutoPorId(id);

        produto.setQuantidade(produto.getQuantidade() + quantidade);

        return produtoRepository.save(produto);
    }

    public Produto saidaEstoque(Long id, int quantidade) {
        Produto produto = obterProdutoPorId(id);

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);

        return produtoRepository.save(produto);
    }

}