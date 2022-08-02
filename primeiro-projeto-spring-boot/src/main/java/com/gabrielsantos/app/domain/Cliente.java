package com.gabrielsantos.app.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer id;
    @Column(name = "cliente_nome", nullable = false)
    @Length(min = 3, message = "Nome inválido, o nome deve possuir no mínimo 3 caracteres!")
    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;
    @Column(name = "cliente_telefone", nullable = false)
    @NotEmpty(message = "O campo telefone é obrigatório!")
    private String telefone;
    @Column(name = "cliente_email", nullable = false)
    @Email(message = "E-mail inválido, informe um e-mail no formato válido, exemplo: usuario@gmail.com")
    private String email;
    @Column(name = "cliente_rg", nullable = false)
    private String rg;
    @Column(name = "cliente_cpf", nullable = false)
    @CPF(message = "Cpf inválido, informe um cpf no formato válido!")
    private String cpf;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente() {}
    public Cliente(Integer id, String nome, String telefone, String email, String rg, String cpf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}