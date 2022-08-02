package com.gabrielsantos.app.service;

import com.gabrielsantos.app.domain.Item;
import com.gabrielsantos.app.domain.Produto;
import com.gabrielsantos.app.dtos.ItemDTO;
import com.gabrielsantos.app.repository.ItemRepository;
import com.gabrielsantos.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity adicionarProdutoNoCarrinho(int idProduto, HttpSession sessao) {
        try {
            Produto produto = this.produtoRepository.findById(idProduto).orElse(null);
            if (produto == null) {
                return new ResponseEntity("Não existe um produto com esse id cadastrado no banco de dados!", HttpStatus.NOT_FOUND);
            }
            if (produto.getQuantidadeUnidadesEstoque() == 0) {
                return new ResponseEntity("Esse produto não possui unidades para venda!", HttpStatus.OK);
            }
            if (sessao.getAttribute("carrinho") == null) {
                List<ItemDTO> itens = new ArrayList<>();
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setIdProduto(produto.getId());
                itemDTO.setQuantidadeUnidadesdoProduto(1);
                itemDTO.setValorItemNoMomentoDaVenda(produto.getPreco());
                itens.add(itemDTO);
                sessao.setAttribute("carrinho", itens);
                return new ResponseEntity(sessao.getAttribute("carrinho"), HttpStatus.OK);
            }
            boolean produtoEstanoCarrinho = false;
            List<ItemDTO> itens = (List<ItemDTO>) sessao.getAttribute("carrinho");
            ItemDTO itemNoCarrinho = null;
            for (ItemDTO itemCarrinho : itens) {
                if (itemCarrinho.getIdProduto() == idProduto) {
                    produtoEstanoCarrinho = true;
                    itemNoCarrinho = itemCarrinho;
                    break;
                }
            }
            if (produtoEstanoCarrinho) {
                int quantidadeAtual = itemNoCarrinho.getQuantidadeUnidadesdoProduto();
                int novaQuantidadeUnidadesDoProduto = quantidadeAtual + 1;
                if (novaQuantidadeUnidadesDoProduto > produto.getQuantidadeUnidadesEstoque()) {
                    throw new Exception("Você não pode adicionar mais unidades desse produto no carrinho!");
                }
                itemNoCarrinho.setQuantidadeUnidadesdoProduto(novaQuantidadeUnidadesDoProduto);
            } else {
                itemNoCarrinho = new ItemDTO();
                itemNoCarrinho.setIdProduto(idProduto);
                itemNoCarrinho.setValorItemNoMomentoDaVenda(produto.getPreco());
                itemNoCarrinho.setQuantidadeUnidadesdoProduto(1);
                itens.add(itemNoCarrinho);
            }
            return new ResponseEntity(sessao.getAttribute("carrinho"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity incrementarUnidadeDeProdutoNoCarrinho(int idProduto, HttpSession sessao) {
        try {
            Produto produto = this.produtoRepository.findById(idProduto).orElse(null);
            if (produto == null) {
                return new ResponseEntity("Não existe um produto cadastrado no banco de dados com esse id!", HttpStatus.NOT_FOUND);
            }
            List<ItemDTO> itens = (List<ItemDTO>) sessao.getAttribute("carrinho");
            ItemDTO item = null;
            for (ItemDTO it : itens) {
                if (it.getIdProduto() == idProduto) {
                    item = it;
                    break;
                }
            }
            int novaQuantidadeUnidadesDoProduto = item.getQuantidadeUnidadesdoProduto() + 1;
            if (novaQuantidadeUnidadesDoProduto > produto.getQuantidadeUnidadesEstoque()) {
                throw new Exception("Você não pode adicionar mais unidades desse produto no carrinho!");
            }
            item.setQuantidadeUnidadesdoProduto(novaQuantidadeUnidadesDoProduto);
            return new ResponseEntity(itens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity decrementarUnidadeDeProdutoNoCarrinho(int idProduto, HttpSession sessao) {
        try {
            Produto produto = this.produtoRepository.findById(idProduto).orElse(null);
            if (produto == null) {
                return new ResponseEntity("Não existe um produto cadastrado no banco de dados com esse id!", HttpStatus.OK);
            }
            List<ItemDTO> itens = (List<ItemDTO>) sessao.getAttribute("carrinho");
            ItemDTO item = null;
            for (ItemDTO it : itens) {
                if (it.getIdProduto() == idProduto) {
                    item = it;
                    break;
                }
            }
            if (item.getQuantidadeUnidadesdoProduto() == 1) {
                itens.remove(item);
                return new ResponseEntity(itens, HttpStatus.OK);
            }
            item.setQuantidadeUnidadesdoProduto(item.getQuantidadeUnidadesdoProduto() - 1);
            return new ResponseEntity(itens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity limparCarrinhoCompras(HttpSession sessao) {
        try {
            sessao.invalidate();
            return new ResponseEntity("Carrinho limpo!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
