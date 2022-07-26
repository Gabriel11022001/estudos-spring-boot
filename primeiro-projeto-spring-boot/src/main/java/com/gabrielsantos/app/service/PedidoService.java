package com.gabrielsantos.app.service;

import com.gabrielsantos.app.domain.Pedido;
import com.gabrielsantos.app.repository.PedidoRepository;
import com.gabrielsantos.app.utils.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Retorno<Pedido> cadastrarPedido(Pedido pedido) {
        try {
            pedido = this.pedidoRepository.save(pedido);
            if (pedido == null) {
                throw new RuntimeException("Ocorreu um erro ao tentar-se cadastrar o pedido, tente novamente!");
            }
            return new Retorno<>("Pedido cadastrado com sucesso!", pedido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Retorno<>(e.getMessage(), null);
        }
    }
}
