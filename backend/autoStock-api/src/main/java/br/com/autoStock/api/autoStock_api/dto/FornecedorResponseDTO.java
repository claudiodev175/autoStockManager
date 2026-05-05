package br.com.autoStock.api.autoStock_api.dto;

import br.com.autoStock.api.autoStock_api.model.Fornecedor;
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
public class FornecedorResponseDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String endereco;
    private String contatoNome;
    private Integer prazoEntrega;
    private String condicaoPagamento;
    private Boolean ativo;

    public static FornecedorResponseDTO fromEntity(Fornecedor fornecedor) {
        return FornecedorResponseDTO.builder()
                .id(fornecedor.getId())
                .nome(fornecedor.getNome())
                .cnpj(fornecedor.getCnpj())
                .email(fornecedor.getEmail())
                .telefone(fornecedor.getTelefone())
                .endereco(fornecedor.getEndereco())
                .contatoNome(fornecedor.getContatoNome())
                .prazoEntrega(fornecedor.getPrazoEntrega())
                .condicaoPagamento(fornecedor.getCondicaoPagamento())
                .ativo(fornecedor.getAtivo())
                .build();
    }
}