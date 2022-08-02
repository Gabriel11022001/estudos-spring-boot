package com.gabrielsantos.app.dtos;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    @Min(value = 0, message = "A quantidade de unidades do produto em estoque deve ser maior ou igual a zero!")
    private Integer quantidadeUnidadesEstoque;
    @NotNull(message = "O campo preço do produto é obrigatório!")
    @Range(min = 0, message = "O preço do produto deve ser maior ou igual a R$0,00!")
    private Double preco;

    public ProdutoDTO() {}
    public ProdutoDTO(Integer id, String nome, String descricao, Integer quantidadeUnidadesEstoque, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeUnidadesEstoque = quantidadeUnidadesEstoque;
        this.preco = preco;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getQuantidadeUnidadesEstoque() {
        return quantidadeUnidadesEstoque;
    }
    public void setQuantidadeUnidadesEstoque(Integer quantidadeUnidadesEstoque) {
        this.quantidadeUnidadesEstoque = quantidadeUnidadesEstoque;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
