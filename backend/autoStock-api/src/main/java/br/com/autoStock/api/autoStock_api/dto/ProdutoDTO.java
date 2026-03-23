package br.com.autoStock.api.autoStock_api.dto;

public record ProdutoDTO(
    String nome,
    String sku,
    String categoria,
    String marca,
    String modeloVeiculo,
    Integer quantidade,
    Double preco
) {}