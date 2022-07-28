package com.gabrielsantos.app.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer id;
    @Column(name = "produto_nome", nullable = false)
    private String nome;
    @Column(name = "produto_descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "produto_qtd_unidades_em_estoque", nullable = false)
    private Integer quantidadeUnidadesEstoque;
    @Column(name = "produto_preco", nullable = false)
    private Double preco;

    public Produto() {}
    public Produto(Integer id, String nome, String descricao, Integer quantidadeUnidadesEstoque, Double preco) {
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
