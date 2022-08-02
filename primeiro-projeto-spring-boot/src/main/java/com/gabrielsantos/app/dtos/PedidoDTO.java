package com.gabrielsantos.app.dtos;

import java.util.List;

public class PedidoDTO {

    private Integer id;
    private ClienteDTO cliente;
    private double valorTotal;
    private List<ProdutoDTORealacionadoAoPedido> produtos;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ClienteDTO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public List<ProdutoDTORealacionadoAoPedido> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoDTORealacionadoAoPedido> produtos) {
        this.produtos = produtos;
    }
}
