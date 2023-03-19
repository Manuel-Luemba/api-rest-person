package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProdutoDto {
    @NotBlank
    private String nome;

    @Min(0)
    private Float preco;

    public ProdutoDto(String nome, Float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }


}
