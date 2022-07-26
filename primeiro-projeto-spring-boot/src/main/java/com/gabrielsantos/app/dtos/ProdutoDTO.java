package com.gabrielsantos.app.dtos;

public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer quantidadeUnidadesEstoque;
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
