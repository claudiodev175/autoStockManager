package br.com.autoStock.api.autoStock_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_produtos")
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String marca; 

    @Column(nullable = false)
    private String modeloVeiculo; 

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double preco;
}