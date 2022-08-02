package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping(value = "/adicionar-produto-no-carrinho/{idProduto}")
    public ResponseEntity adicionarProdutoNoCarrinho(@PathVariable("idProduto") int idProduto, HttpSession sessao) {
        return this.carrinhoService.adicionarProdutoNoCarrinho(idProduto, sessao);
    }
    @GetMapping(value = "/incrementar/{idProduto}")
    public ResponseEntity incrementarUnidadeDoProdutoNoCarrinho(@PathVariable("idProduto") int idProduto, HttpSession sessao) {
        return this.carrinhoService.incrementarUnidadeDeProdutoNoCarrinho(idProduto, sessao);
    }
    @GetMapping(value = "/decrementar/{idProduto}")
    public ResponseEntity decrementarUnidadeDoProdutoNoCarrinho(@PathVariable("idProduto") int idProduto, HttpSession sessao) {
        return this.carrinhoService.decrementarUnidadeDeProdutoNoCarrinho(idProduto, sessao);
    }
    @GetMapping(value = "/limpar-carrinho-compras")
    public ResponseEntity limparCarrinhoCompras(HttpSession sessao) {
        return this.carrinhoService.limparCarrinhoCompras(sessao);
    }
}
