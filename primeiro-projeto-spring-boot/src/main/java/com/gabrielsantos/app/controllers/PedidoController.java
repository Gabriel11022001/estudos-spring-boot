package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.dtos.PedidoDTO;
import com.gabrielsantos.app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/efetivar-pedido")
    public ResponseEntity confirmarPedido(HttpSession sessao) {
        return this.pedidoService.efetivarPedido(sessao);
    }
    @GetMapping(value = "/cancelar-pedido")
    public ResponseEntity cancelarPedido(HttpSession sessao) {
        return null;
    }
    @GetMapping
    public ResponseEntity buscarTodosPedidos() {
        return null;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity buscarPedidoPeloId(@PathVariable("id") Integer id) {
        return this.pedidoService.buscarPedidoPeloId(id);
    }
}
