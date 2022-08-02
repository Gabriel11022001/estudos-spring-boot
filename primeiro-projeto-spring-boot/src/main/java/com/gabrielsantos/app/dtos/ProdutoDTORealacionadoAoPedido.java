package com.gabrielsantos.app.dtos;

public class ProdutoDTORealacionadoAoPedido {

    private Integer id;
    private String produto;
    private double preco;
    private Integer quantidadeUnidadesNoPedido;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public Integer getQuantidadeUnidadesNoPedido() {
        return quantidadeUnidadesNoPedido;
    }
    public void setQuantidadeUnidadesNoPedido(Integer quantidadeUnidadesNoPedido) {
        this.quantidadeUnidadesNoPedido = quantidadeUnidadesNoPedido;
    }
}
