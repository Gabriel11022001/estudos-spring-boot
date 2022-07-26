package com.gabrielsantos.app.utils;

public class Retorno<T> {

    private String descricao;
    private T conteudo;

    public Retorno(String descricao, T conteudo) {
        this.descricao = descricao;
        this.conteudo = conteudo;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public T getConteudo() {
        return this.conteudo;
    }
}
