package com.gabrielsantos.app.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "O campo nome é obrigatório!")
    private String nome;
    @NotEmpty(message = "O campo telefone é obrigatório!")
    private String telefone;
    @Email(message = "E-mail inválido, informe um e-mail válido!")
    private String email;
    private String rg;
    @NotEmpty(message = "O campo cpf é obrigatório!")
    @CPF(message = "Cpf inválido, informe um cpf válido!")
    private String cpf;

    public ClienteDTO() {}
    public ClienteDTO(Integer id, String nome, String telefone, String email, String rg, String cpf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRg() {
        return this.rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getCpf() {
        return this.cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    @Override
    public String toString() {
        return "Id: " + this.getId() + "\nNome: " + this.getNome() + "\nE-mail: " + this.getEmail() + "\nTelefone: " + this.getTelefone() + "\nRg: " + this.getRg() + "\nCpf: " + this.getCpf();
    }
}
