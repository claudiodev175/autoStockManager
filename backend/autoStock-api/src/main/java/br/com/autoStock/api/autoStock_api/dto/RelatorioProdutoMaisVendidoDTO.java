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
public class RelatorioProdutoMaisVendidoDTO {
    private Long produtoId;
    private String produtoNome;
    private String sku;
    private Long totalQuantidadeSaida;
    private Double totalValorVendido;
}