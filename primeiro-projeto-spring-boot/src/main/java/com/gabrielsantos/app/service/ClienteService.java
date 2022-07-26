package com.gabrielsantos.app.service;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.domain.Pedido;
import com.gabrielsantos.app.dtos.ClienteDTO;
import com.gabrielsantos.app.repository.ClienteRepository;
import com.gabrielsantos.app.utils.Retorno;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity salvar(ClienteDTO cliente) {
        try {
            if (cliente.getNome() == null || cliente.getCpf() == null
            || cliente.getRg() == null || cliente.getTelefone() == null
            || cliente.getEmail() == null || cliente.getNome().isEmpty()
            || cliente.getTelefone().isEmpty() || cliente.getRg().isEmpty()
            || cliente.getCpf().isEmpty() || cliente.getEmail().isEmpty()) {
                throw new RuntimeException("Preencha todos os campos obrigatórios!");
            }
            Cliente clienteACadastrar = new Cliente();
            clienteACadastrar.setNome(cliente.getNome());
            clienteACadastrar.setEmail(cliente.getEmail());
            clienteACadastrar.setTelefone(cliente.getTelefone());
            clienteACadastrar.setCpf(cliente.getCpf());
            clienteACadastrar.setRg(cliente.getRg());
            clienteACadastrar = this.clienteRepository.save(clienteACadastrar);
            if (cliente == null) {
                throw new RuntimeException("Ocorreu um erro ao tentar-se cadastradar o cliente, tente novamente!");
            }
            cliente.setId(clienteACadastrar.getId());
            return ResponseEntity.ok(new Retorno<ClienteDTO>("Cliente cadastrado com sucesso!", cliente));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity remover(Integer id) {
        try {
            Cliente clienteRemover = this.clienteRepository.findById(id).orElse(null);
            if (clienteRemover == null) {
                throw new RuntimeException("Não existe um cliente cadastrado com esse id no banco de dados!");
            }
            this.clienteRepository.delete(clienteRemover);
            System.out.println("Cliente removido com sucesso!");
            return ResponseEntity.ok("Cliente de id " + id + " removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity atualizar(ClienteDTO clienteDTO) {
        try {
            if (clienteDTO.getId() == null || clienteDTO.getNome() == null
            || clienteDTO.getEmail() == null || clienteDTO.getTelefone() == null
            || clienteDTO.getRg() == null || clienteDTO.getCpf() == null
            || clienteDTO.getNome().isEmpty() || clienteDTO.getEmail().isEmpty()
            || clienteDTO.getTelefone().isEmpty() || clienteDTO.getRg().isEmpty()
            || clienteDTO.getCpf().isEmpty()) {
                throw new Exception("Preencha todos os campos obrigatórios!");
            }
            if (clienteDTO.getId() <= 0) {
                throw new Exception("O id do cliente não pode ser menor ou igual a zero!");
            }
            Cliente clienteAtualizar = this.clienteRepository.findById(clienteDTO.getId()).orElse(null);
            if (clienteAtualizar == null) {
                throw new Exception("Não existe um cliente cadastrado com esse id!");
            }
            clienteAtualizar.setNome(clienteDTO.getNome());
            clienteAtualizar.setRg(clienteAtualizar.getRg());
            clienteAtualizar.setTelefone(clienteAtualizar.getTelefone());
            clienteAtualizar.setEmail(clienteAtualizar.getEmail());
            clienteAtualizar.setCpf(clienteAtualizar.getCpf());
            this.clienteRepository.save(clienteAtualizar);
            return ResponseEntity.ok(new Retorno<ClienteDTO>("Cliente atualizado com sucesso!", clienteDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity buscarPeloId(Integer id) {
        try {
            Cliente cliente = this.clienteRepository.findById(id).orElse(null);
            if (cliente == null) {
                throw new Exception("Não existe um cliente cadastrado com esse id!");
            }
            ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getRg(), cliente.getCpf());
            return ResponseEntity.ok(clienteDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity buscarTodos() {
        try {
            List<Cliente> clientes = this.clienteRepository.findAll();
            if (clientes.size() == 0) {
                throw new RuntimeException("Não existem clientes cadastrados no banco de dados!");
            }
            List<ClienteDTO> clienteDTOS = new ArrayList<>();
            clientes.forEach((c) -> {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setId(c.getId());
                clienteDTO.setNome(c.getNome());
                clienteDTO.setRg(c.getRg());
                clienteDTO.setEmail(c.getEmail());
                clienteDTO.setTelefone(c.getTelefone());
                clienteDTO.setCpf(c.getCpf());
                clienteDTOS.add(clienteDTO);
            });
            if (clientes.size() == 1) {
                return ResponseEntity.ok(new Retorno<List<ClienteDTO>>("Existe 1 cliente cadastrado no banco de dados!", clienteDTOS));
            }
            return ResponseEntity.ok(new Retorno<List<ClienteDTO>>("Existe um total de " + clienteDTOS.size() + " clientes cadastrados no banco de dados!", clienteDTOS));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Retorno<List<Cliente>> buscarClientesOrdenadosDeFormaDecrescente() {
        try {
            List<Cliente> clientes = this.clienteRepository.buscarTodosClientesOrdenadosPeloNomeDecrescente();
            if (clientes.size() == 0) {
                throw new RuntimeException("Não existem clientes cadastrados no banco de dados!");
            }
            if (clientes.size() == 1) {
                return new Retorno<>("Existe 1 cliente cadastrado no banco de dados!", clientes);
            }
            return new Retorno<>("Existe no total " + clientes.size() + " cadastrados no banco de dados!", clientes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Retorno<>(e.getMessage(), new ArrayList<>());
        }
    }
    public ResponseEntity buscarClientesQueContemNome(String nome) {
        try {
            List<Cliente> clientes = this.clienteRepository.buscarClientePeloNomeComContem(nome);
            if (clientes.size() == 0) {
                throw new RuntimeException("Nenhum cliente contem esse nome!");
            }
            List<ClienteDTO> clienteDTOS = new ArrayList<>();
            clientes.forEach((c) -> {
                clienteDTOS.add(new ClienteDTO(
                        c.getId(),
                        c.getNome(),
                        c.getTelefone(),
                        c.getEmail(),
                        c.getRg(),
                        c.getCpf()
                ));
            });
            if (clientes.size() == 1) {
                return ResponseEntity.ok(new Retorno<List<ClienteDTO>>("Existe um cliente com esse nome!", clienteDTOS));
            }
            return ResponseEntity.ok(new Retorno<List<ClienteDTO>>("Existe no total " + clienteDTOS.size() + " clientes com esse nome!", clienteDTOS));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Retorno<List<Pedido>> buscarTodosOsPedidosDeUmCliente(Integer idCliente) {
        try {
            List<Pedido> pedidosDoCliente = this.clienteRepository.buscarTodosPedidosDeUmCliente(idCliente);
            if (pedidosDoCliente.size() == 0) {
                throw new RuntimeException("Esse cliente não possui pedidos!");
            }
            if (pedidosDoCliente.size() == 1) {
                return new Retorno<>("Esse cliente possui 1 pedido!", pedidosDoCliente);
            }
            return new Retorno<>("Esse cliente possui " + pedidosDoCliente.size() + " pedidos!", pedidosDoCliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Retorno<>(e.getMessage(), new ArrayList<>());
        }
    }
    public ResponseEntity buscarClientePeloCpf(String cpf) {
        try {
            if (cpf == null || cpf.isEmpty()) {
                throw new Exception("Informe o cpf do cliente!");
            }
            Cliente cliente = this.clienteRepository.buscarClientePeloCpf(cpf);
            if (cliente == null) {
                return new ResponseEntity("Não existe um cliente cadastrado com esse cpf!", HttpStatus.OK);

            }
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setCpf(cliente.getCpf());
            clienteDTO.setRg(cliente.getRg());
            clienteDTO.setTelefone(cliente.getTelefone());
            clienteDTO.setEmail(cliente.getEmail());
            return new ResponseEntity(
                    new Retorno<ClienteDTO>(
                            "Cliente encontrado com sucesso!",
                            clienteDTO
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
