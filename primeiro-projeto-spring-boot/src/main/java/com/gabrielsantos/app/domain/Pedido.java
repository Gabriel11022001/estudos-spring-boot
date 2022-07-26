package com.gabrielsantos.app.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Integer id;
    @Column(name = "pedido_data_cadastro", nullable = false)
    private LocalDateTime dataCadastrado;
    @Column(name = "pedido_valor_total", nullable = false)
    private Double valorTotal;
    @Column(name = "pedido_previsao_entrega", nullable = false)
    private LocalDate dataPrevisaoEntrega;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {}
    public Pedido(Integer id, LocalDateTime dataCadastrado, Double valorTotal, LocalDate dataPrevisaoEntrega, Cliente cliente) {
        this.id = id;
        this.dataCadastrado = dataCadastrado;
        this.valorTotal = valorTotal;
        this.dataPrevisaoEntrega = dataPrevisaoEntrega;
        this.cliente = cliente;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getDataCadastrado() {
        return dataCadastrado;
    }
    public void setDataCadastrado(LocalDateTime dataCadastrado) {
        this.dataCadastrado = dataCadastrado;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public LocalDate getDataPrevisaoEntrega() {
        return dataPrevisaoEntrega;
    }
    public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
        this.dataPrevisaoEntrega = dataPrevisaoEntrega;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
