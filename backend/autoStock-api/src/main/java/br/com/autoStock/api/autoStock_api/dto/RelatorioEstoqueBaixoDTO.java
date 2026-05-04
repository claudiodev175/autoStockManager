package br.com.autoStock.api.autoStock_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioEstoqueBaixoDTO {
    private Long id;
    private String nome;
    private String sku;
    private Integer quantidade;
    private Double preco;
    private String categoria;
    private String marca;
}