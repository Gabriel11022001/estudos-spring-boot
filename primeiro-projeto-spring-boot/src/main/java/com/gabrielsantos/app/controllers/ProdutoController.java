package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.dtos.ProdutoDTO;
import com.gabrielsantos.app.service.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return this.produtoService.cadastrarProduto(produtoDTO);
    }
    @GetMapping
    public ResponseEntity buscarTodosProdutos() {
        return this.produtoService.buscarTodosProdutos();
    }
    @PutMapping
    public ResponseEntity editarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return this.produtoService.editarProduto(produtoDTO);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity buscarProdutoPeloId(@PathVariable("id") Integer id) {
        return this.produtoService.buscarProdutoPeloId(id);
    }
    @GetMapping(value = "/buscar-entre-precos")
    public ResponseEntity buscarProdutosEntreRangeDePrecos(@RequestParam("precoInicial") double precoInicial, @RequestParam("precoFinal") double precoFinal) {
        return this.produtoService.buscarProdutosEntrePrecos(precoInicial, precoFinal);
    }
}
