package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    public Cliente salvar(Cliente cliente) {
        String query = "insert into tbl_clientes(cliente_nome, cliente_telefone, cliente_email, cliente_rg, cliente_cpf) values(?, ?, ?, ? ,?)";
        Object[] parametros = new Object[5];
        parametros[0] = cliente.getNome();
        parametros[1] = cliente.getTelefone();
        parametros[2] = cliente.getEmail();
        parametros[3] = cliente.getRg();
        parametros[4] = cliente.getCpf();
        // System.out.println(this.jdbcTemplate);
        this.jdbcTemplate.update(query, parametros);
        return cliente;
    }
    @Transactional
    public void cadastrarClienteComJpa(Cliente cliente) {
        this.entityManager.persist(cliente);
    }
    @Transactional
    public void removerClienteComJpa(Cliente cliente) {
        this.entityManager.remove(cliente);
    }
    @Transactional
    public List<Cliente> listarTodosOsClientesComJpa() {
        List<Cliente> clientes = this.entityManager.createQuery("select c from Cliente c").getResultList();
        return clientes;
    }
    @Transactional
    public Cliente buscarClientePeloIdComJpa(int id) {
        Cliente cliente = this.entityManager.find(Cliente.class, id);
        return cliente;
    }
}
