package com.gabrielsantos.app.service;

import com.gabrielsantos.app.domain.Cliente;
import com.gabrielsantos.app.domain.Item;
import com.gabrielsantos.app.domain.Pedido;
import com.gabrielsantos.app.domain.Produto;
import com.gabrielsantos.app.dtos.ClienteDTO;
import com.gabrielsantos.app.dtos.ItemDTO;
import com.gabrielsantos.app.dtos.PedidoDTO;
import com.gabrielsantos.app.dtos.ProdutoDTORealacionadoAoPedido;
import com.gabrielsantos.app.repository.ClienteRepository;
import com.gabrielsantos.app.repository.ItemRepository;
import com.gabrielsantos.app.repository.PedidoRepository;
import com.gabrielsantos.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Sempre utilizar a anotação @Transactional quando o método realizar
     * mais de uma operação no banco de dados, pois caso seja lançado alguma exceção
     * durante a persistencia de alguma entidade no banco de dados, será realizado um rollback.
     * @param sessao
     * @return objeto do tipo ResponseEntity
     */
    @Transactional
    public ResponseEntity efetivarPedido(HttpSession sessao) {
        try {
            List<ItemDTO> itensPedido = (List<ItemDTO>) sessao.getAttribute("carrinho");
            if (itensPedido.size() == 0) {
                throw new Exception("Não existem produtos no carrinho de compras!");
            }
            double valorTotalVenda = 0;
            for (ItemDTO item : itensPedido) {
                valorTotalVenda += (item.getValorItemNoMomentoDaVenda() * item.getQuantidadeUnidadesdoProduto());
            }
            // Pegando um cliente qualquer
            Cliente clienteVenda = this.clienteRepository.findById(1).orElse(null);
            Pedido pedido = new Pedido();
            pedido.setCliente(clienteVenda);
            pedido.setDataCadastrado(LocalDateTime.now());
            pedido.setDataPrevisaoEntrega(LocalDate.of(2022, 9, 12));
            pedido.setValorTotal(valorTotalVenda);
            // Salvando o pedido no banco de dados
            pedido = this.pedidoRepository.save(pedido);
            if (pedido == null) {
                throw new Exception("Ocorreu um erro ao tentar-se cadastrar o pedido no banco de dados!");
            }
            List<Item> itens = new ArrayList<>();
            // Buscando todos os produtos do pedido e adicionando os itens na lista de itens de pedido
            for (ItemDTO it : itensPedido) {
                Produto produto = this.produtoRepository.findById(it.getIdProduto()).orElse(null);
                if (produto == null) {
                    throw new Exception("Produto não encontrado!");
                }
                Item itemPedido = new Item();
                itemPedido.setPedido(pedido);
                itemPedido.setProduto(produto);
                itemPedido.setValorItemNoMomentoDaVenda(it.getValorItemNoMomentoDaVenda());
                itemPedido.setQuantidadeUnidadesdoProduto(it.getQuantidadeUnidadesdoProduto());
                itens.add(itemPedido);
            }
            // Decrementando a quantidade de unidades dos produtos do carrinho
            for (Item item : itens) {
                Produto p = item.getProduto();
                p.setQuantidadeUnidadesEstoque(p.getQuantidadeUnidadesEstoque() - item.getQuantidadeUnidadesdoProduto());
            }
            // Salvando as entidades produtos
            for (Item item : itens) {
                Produto p = item.getProduto();
                p = this.produtoRepository.save(p);
                if (p == null) {
                    throw new Exception("Ocorreu um erro ao tentar-se decrementar a unidade do produto no estoque!");
                }
            }
            // Salvando os itens do pedido
            itens = this.itemRepository.saveAll(itens);
            if (itens == null) {
                throw new Exception("Ocorreu um erro ao tentar-se salvar os itens do pedido no banco de dados!");
            }
            sessao.invalidate();
            return new ResponseEntity("Pedido cadastrado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity cancelarPedido(int idPedido) {
        return null;
    }
    public ResponseEntity buscarPedidoPeloId(Integer id) {
        try {
            Pedido pedido = this.pedidoRepository.findById(id).orElse(null);
            if (pedido == null) {
                return new ResponseEntity("Não existe um pedido cadastrado no banco de dados com esse id!", HttpStatus.OK);
            }
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setValorTotal(pedido.getValorTotal());
            pedidoDTO.setId(pedido.getId());
            Cliente cliente = pedido.getCliente();
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setTelefone(cliente.getTelefone());
            clienteDTO.setRg(cliente.getRg());
            clienteDTO.setCpf(cliente.getCpf());
            pedidoDTO.setCliente(clienteDTO);
            List<ProdutoDTORealacionadoAoPedido> produtos = new ArrayList<>();
            List<Item> itens = this.itemRepository.buscarItensPeloIdDoPedido(id);
            itens.forEach((item) -> {
                Produto produto = item.getProduto();
                ProdutoDTORealacionadoAoPedido produtoDTORealacionadoAoPedido = new ProdutoDTORealacionadoAoPedido();
                produtoDTORealacionadoAoPedido.setId(produto.getId());
                produtoDTORealacionadoAoPedido.setPreco(item.getValorItemNoMomentoDaVenda());
                produtoDTORealacionadoAoPedido.setQuantidadeUnidadesNoPedido(item.getQuantidadeUnidadesdoProduto());
                produtos.add(produtoDTORealacionadoAoPedido);
            });
            pedidoDTO.setProdutos(produtos);
            return new ResponseEntity(pedidoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
