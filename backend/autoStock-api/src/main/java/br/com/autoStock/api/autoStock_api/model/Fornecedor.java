package br.com.autoStock.api.autoStock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_fornecedores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cnpj;

    private String email;

    private String telefone;

    private String endereco;

    private String contatoNome; // Nome da pessoa de contato

    private Integer prazoEntrega; // Dias úteis

    private String condicaoPagamento;

    @Builder.Default
    private Boolean ativo = true; // Para desativar fornecedor sem deletar
}