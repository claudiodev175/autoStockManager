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
public class RelatorioMovimentacaoPeriodoDTO {
    private String data;
    private Long quantidadeEntrada;
    private Long quantidadeSaida;
    private Double valorTotalEntrada;
    private Double valorTotalSaida;
}