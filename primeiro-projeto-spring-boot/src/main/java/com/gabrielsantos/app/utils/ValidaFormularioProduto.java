package com.gabrielsantos.app.utils;

import com.gabrielsantos.app.dtos.ProdutoDTO;

public class ValidaFormularioProduto implements IValidaFormulario<ProdutoDTO> {

    @Override
    public Boolean validarFormulario(ProdutoDTO entidade) {
        if (entidade.getNome() == null || entidade.getNome().isEmpty()
        || entidade.getDescricao() == null || entidade.getDescricao().isEmpty()
        || entidade.getQuantidadeUnidadesEstoque() == null || entidade.getPreco() == null) {
            throw new RuntimeException("Preencha todos os campos obrigatórios!");
        }
        if (entidade.getQuantidadeUnidadesEstoque() < 0) {
            throw new RuntimeException("Você não pode definir que a quantidade de unidades de um produto em estoque é menor que 0!");
        }
        if (entidade.getPreco() < 0) {
            throw new RuntimeException("Você não pode definir um preço negativo para o produto!");
        }
        return true;
    }
}
