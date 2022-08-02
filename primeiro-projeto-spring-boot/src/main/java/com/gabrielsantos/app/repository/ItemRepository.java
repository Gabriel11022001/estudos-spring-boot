package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select it from Item it where id.pedido.id = :idPedido")
    List<Item> buscarItensPeloIdDoPedido(@Param("idPedido") int idPedido);
}
