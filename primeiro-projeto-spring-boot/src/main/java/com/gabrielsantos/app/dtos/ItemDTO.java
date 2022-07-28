package com.gabrielsantos.app.dtos;

import com.gabrielsantos.app.domain.Produto;

public class ItemDTO {

    private Integer id;
    private Integer idProduto;
    private Integer idPedido;
    private double valorItemNoMomentoDaVenda;
    private Integer quantidadeUnidadesdoProduto;
    private Integer quantidadeUnidadesDisponiveisAtualmente;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
    public Integer getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public double getValorItemNoMomentoDaVenda() {
        return valorItemNoMomentoDaVenda;
    }
    public void setValorItemNoMomentoDaVenda(double valorItemNoMomentoDaVenda) {
        this.valorItemNoMomentoDaVenda = valorItemNoMomentoDaVenda;
    }
    public Integer getQuantidadeUnidadesdoProduto() {
        return quantidadeUnidadesdoProduto;
    }
    public void setQuantidadeUnidadesdoProduto(Integer quantidadeUnidadesdoProduto) {
        this.quantidadeUnidadesdoProduto = quantidadeUnidadesdoProduto;
    }
    public Integer getQuantidadeUnidadesDisponiveisAtualmente() {
        return quantidadeUnidadesDisponiveisAtualmente;
    }
    public void setQuantidadeUnidadesDisponiveisAtualmente(Integer quantidadeUnidadesDisponiveisAtualmente) {
        this.quantidadeUnidadesDisponiveisAtualmente = quantidadeUnidadesDisponiveisAtualmente;
    }
}
