package com.gabrielsantos.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pedido_id")
    private Integer id;
    @Column(name = "item_pedido_preco_produto_no_momento", nullable = false)
    private Double valorDoProdutoNoMomento;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {}
    public ItemPedido(Integer id, Double valorDoProdutoNoMomento, Produto produto, Pedido pedido) {
        this.id = id;
        this.valorDoProdutoNoMomento = valorDoProdutoNoMomento;
        this.produto = produto;
        this.pedido = pedido;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getValorDoProdutoNoMomento() {
        return valorDoProdutoNoMomento;
    }
    public void setValorDoProdutoNoMomento(Double valorDoProdutoNoMomento) { this.valorDoProdutoNoMomento = valorDoProdutoNoMomento; }
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
}
