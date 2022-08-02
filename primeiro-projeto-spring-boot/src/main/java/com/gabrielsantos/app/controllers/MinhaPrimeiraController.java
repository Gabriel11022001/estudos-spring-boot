package com.gabrielsantos.app.controllers;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.domain.Pedido;
import com.gabrielsantos.app.repository.ClienteDAO;
import com.gabrielsantos.app.service.ClienteService;
import com.gabrielsantos.app.service.PedidoService;
import com.gabrielsantos.app.utils.Retorno;
import com.gabrielsantos.app.utils.TesteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class MinhaPrimeiraController {

    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/ola-mundo")
    public String olaMundo() {
        return "Olá Mundo!";
    }
    @GetMapping(value = "/listar-nomes-pessoas")
    public List<String> listarNomesPessoas() {
        return Arrays.asList("Gabriel", "Maria", "Pedro", "Gustavo", "Maria Eduarda");
    }
    @GetMapping(value = "/testar-cadastro-clientes")
    public void testarCadastroDeClientes() {
        Cliente cliente = new Cliente(
                null,
                "Gabriel Rodrigues dos Santos",
                "(12) 123456789",
                "gabriel@teste.com",
                "12.678.987-98",
                "123.456.789-00"
        );
        try {
            // cliente = this.clienteDAO.salvar(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    @GetMapping(value = "/cadastrar-cliente-com-jpa")
    public void cadastrarClienteComJpa() {
        Cliente cliente = new Cliente();
        try {
            cliente.setNome("Pedro");
            cliente.setEmail("pedro@gmail.com");
            cliente.setTelefone("(11)12345689");
            cliente.setCpf("122.345.678-44");
            cliente.setRg("12.678.543-98");
            // this.clienteDAO.cadastrarClienteComJpa(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    @GetMapping(value = "/listar-todos-clientes-com-jpa")
    public List<Cliente> listarTodosOsClientesComJsp() {
        try {
            List<Cliente> clientes = this.clienteDAO.listarTodosOsClientesComJpa();
            if (clientes.size() == 0) {
                throw new Exception("Não existem clientes cadastrados no banco de dados!");
            }
            return clientes;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    @GetMapping(value = "/remover-cliente-com-jpa")
    public void removerClienteComJsp() {
        Cliente clienteRemover = null;
        try {
            clienteRemover = this.clienteDAO.buscarClientePeloIdComJpa(1);
            if (clienteRemover == null) {
                throw new Exception("Não existe um cliente cadastrado com esse id!");
            }
            this.clienteDAO.removerClienteComJpa(clienteRemover);
            System.out.println("Cliente de id " + clienteRemover.getId() + " removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    @GetMapping(value = "/buscar-cliente-pelo-id-com-jpa")
    public Cliente buscarClientePeloIdComJpa() {
        Cliente cliente = null;
        try {
            cliente = this.clienteDAO.buscarClientePeloIdComJpa(2);
            if (cliente == null) {
                throw new Exception("Não existe um cliente cadastrado no banco de dados com esse id!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return cliente;
    }
    @GetMapping(value = "/buscar-todos-cliente-com-spring-data-jpa")
    public Retorno<List<Cliente>> buscarTodosOsClientesComSpringDataJpa() {
        //return this.clienteService.buscarTodos();
        return null;
    }
    @GetMapping(value = "/cadastrar-cliente-com-spring-data-jpa")
    public Retorno<Cliente> cadastrarClienteComSpringDataJpa() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Fernanda da Silva");
        cliente.setRg("123.456.134-90");
        cliente.setCpf("124.567.434-98");
        cliente.setEmail("maria@teste.com");
        // cliente.setTelefone(null);
        //cliente.setEmail("");
        cliente.setTelefone("(11)998776655");
        //return this.clienteService.salvar(cliente);
        return null;
    }
    @GetMapping(value = "/buscar-clientes-ordenados-de-forma-decrescente")
    public Retorno<List<Cliente>> buscarClientesOrdenadosDeFormaDecrescente() {
        return this.clienteService.buscarClientesOrdenadosDeFormaDecrescente();
    }
    @GetMapping(value = "/buscar-clientes-que-contem-nome")
    public Retorno<List<Cliente>> buscarClientesQueContemNome() {
        String nome = "Gab";
        // return this.clienteService.buscarClientesQueContemNome(nome);
        return null;
    }
    @GetMapping(value = "/buscar-todos-pedidos-de-um-cliente")
    public Retorno<List<Pedido>> buscarTodosPedidosDeUmCliente() {
        return this.clienteService.buscarTodosOsPedidosDeUmCliente(2);
    }
    @GetMapping(value = "/cadastrar-pedido-com-jpa")
    public Retorno<Pedido> cadastrarPedido() {
        Cliente clienteDoPedido = null;
        //Cliente clienteDoPedido = this.clienteService.buscarPeloId(1);
        System.out.println(clienteDoPedido);
        Pedido pedido = new Pedido();
        pedido.setValorTotal(400.0);
        pedido.setDataCadastrado(LocalDateTime.now());
        pedido.setDataPrevisaoEntrega(LocalDate.of(2022, 9, 11));
        pedido.setCliente(clienteDoPedido);
        // return this.pedidoService.cadastrarPedido(pedido);
        return null;
    }
    @GetMapping(value = "/testar-exception")
    public ResponseEntity testarException() {
        throw new TesteException("Teste da exceção!");
    }
}
