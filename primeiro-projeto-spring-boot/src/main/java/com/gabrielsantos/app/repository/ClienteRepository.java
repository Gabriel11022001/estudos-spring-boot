package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.nome = :nome")
    Cliente buscarClientePeloNome(@Param("nome") String nome);
    @Query("select c from Cliente c where c.nome like %:nome%")
    List<Cliente> buscarClientePeloNomeComContem(@Param("nome") String nome);
    @Query("select c from Cliente c order by c.nome desc")
    List<Cliente> buscarTodosClientesOrdenadosPeloNomeDecrescente();
    @Query("select p from Pedido p where p.cliente.id = :idCliente")
    List<Pedido> buscarTodosPedidosDeUmCliente(@Param("idCliente") Integer idCliente);
}
