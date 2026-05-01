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
public class FornecedorRequestDTO {
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String endereco;
    private String contatoNome;
    private Integer prazoEntrega;
    private String condicaoPagamento;
}