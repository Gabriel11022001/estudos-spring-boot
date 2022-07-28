package com.gabrielsantos.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;
    private double valorItemNoMomentoDaVenda;
    private Integer quantidadeUnidadesdoProduto;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
}
