package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.dtos.ClienteDTO;
import com.gabrielsantos.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/ola/{nome}")
    @ResponseBody
    public String olaCliente(@PathVariable("nome") String nome) {
        return "Olá " + nome + " ,como vai?";
    }
    @GetMapping(value = "/busca-pelo-id/{id}")
    @ResponseBody
    public ResponseEntity buscarClientePeloId(@PathVariable("id") Integer id) {
        return this.clienteService.buscarPeloId(id);
    }
    @GetMapping(value = "/buscar-todos")
    @ResponseBody
    public ResponseEntity buscarTodosClientes() {
        return this.clienteService.buscarTodos();
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        // System.out.println(clienteDTO);
        return this.clienteService.salvar(clienteDTO);
    }
    @DeleteMapping(value = "/remover/{id}")
    @ResponseBody
    public ResponseEntity removerCliente(@PathVariable("id") Integer id) {
        return this.clienteService.remover(id);
    }
    @PutMapping
    @ResponseBody
    public ResponseEntity atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        return this.clienteService.atualizar(clienteDTO);
    }
    @GetMapping(value = "/buscar-clientes-que-contem-nome/{nome}")
    public ResponseEntity buscarClientesQueContemNome(@PathVariable("nome") String nome) {
        return this.clienteService.buscarClientesQueContemNome(nome);
    }
}
