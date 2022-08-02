package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.dtos.ClienteDTO;
import com.gabrielsantos.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/ola/{nome}")
    public String olaCliente(@PathVariable("nome") String nome) {
        return "Olá " + nome + " ,como vai?";
    }
    @GetMapping(value = "/busca-pelo-id/{id}")
    public ResponseEntity buscarClientePeloId(@PathVariable("id") Integer id) {
        return this.clienteService.buscarPeloId(id);
    }
    @GetMapping(value = "/buscar-todos")
    public ResponseEntity buscarTodosClientes() {
        return this.clienteService.buscarTodos();
    }
    @PostMapping
    public ResponseEntity cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        // System.out.println(clienteDTO);
        return this.clienteService.salvar(clienteDTO);
    }
    @DeleteMapping(value = "/remover/{id}")
    public ResponseEntity removerCliente(@PathVariable("id") Integer id) {
        return this.clienteService.remover(id);
    }
    @PutMapping
    public ResponseEntity atualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        return this.clienteService.atualizar(clienteDTO);
    }
    @GetMapping(value = "/buscar-clientes-que-contem-nome/{nome}")
    public ResponseEntity buscarClientesQueContemNome(@PathVariable("nome") String nome) {
        return this.clienteService.buscarClientesQueContemNome(nome);
    }
    @GetMapping(value = "/buscar-pelo-cpf/{cpf}")
    public ResponseEntity buscarClientePeloCpf(@PathVariable("cpf") String cpf) {
        return this.clienteService.buscarClientePeloCpf(cpf);
    }
}
