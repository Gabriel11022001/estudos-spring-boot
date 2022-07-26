package com.gabrielsantos.app.service;

import com.gabrielsantos.app.domain.Produto;
import com.gabrielsantos.app.dtos.ProdutoDTO;
import com.gabrielsantos.app.repository.ProdutoRepository;
import com.gabrielsantos.app.utils.IValidaFormulario;
import com.gabrielsantos.app.utils.Retorno;
import com.gabrielsantos.app.utils.ValidaFormularioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity cadastrarProduto(ProdutoDTO produtoDTO) {

        IValidaFormulario validaFormulario = new ValidaFormularioProduto();
        try {
            validaFormulario.validarFormulario(produtoDTO);
            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setDescricao(produtoDTO.getDescricao());
            produto.setPreco(produtoDTO.getPreco());
            produto.setQuantidadeUnidadesEstoque(produtoDTO.getQuantidadeUnidadesEstoque());
            produto = this.produtoRepository.save(produto);
            if (produto == null) {
                throw new RuntimeException("Ocorreu um erro ao tentar-se cadastrar o produto, tente novamente!");
            }
            produtoDTO.setId(produto.getId());
            return new ResponseEntity(
                    new Retorno<ProdutoDTO>(
                            "Produto cadastrado com sucesso!",
                            produtoDTO
                    ),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    public ResponseEntity buscarTodosProdutos() {
        try {
            List<Produto> produtos = this.produtoRepository.findAll();
            if (produtos.size() == 0) {
                return new ResponseEntity(
                        "Não existem produtos cadastrados no banco de dados!",
                        HttpStatus.OK
                );
            }
            List<ProdutoDTO> produtoDTOS = new ArrayList<>();
            produtos.forEach((p) -> {
                produtoDTOS.add(new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getQuantidadeUnidadesEstoque(),
                        p.getPreco()
                ));
            });
            return new ResponseEntity(produtoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    public ResponseEntity editarProduto(ProdutoDTO produtoDTO) {
        try {
            ValidaFormularioProduto validaFormularioProduto = new ValidaFormularioProduto();
            validaFormularioProduto.validarFormulario(produtoDTO);
            Produto produtoASerAtualizado = this.produtoRepository.findById(produtoDTO.getId()).orElse(null);
            if (produtoASerAtualizado == null) {
                throw new RuntimeException("Não existe um produto cadastrado com esse id no banco de dados!");
            }
            produtoASerAtualizado.setNome(produtoDTO.getNome());
            produtoASerAtualizado.setDescricao(produtoDTO.getDescricao());
            produtoASerAtualizado.setPreco(produtoDTO.getPreco());
            produtoASerAtualizado.setQuantidadeUnidadesEstoque(produtoDTO.getQuantidadeUnidadesEstoque());
            produtoASerAtualizado = this.produtoRepository.save(produtoASerAtualizado);
            if (produtoASerAtualizado == null) {
                throw new RuntimeException("Ocorreu um erro ao tentar-se atualizar o produto de id " + produtoDTO.getId() + " no banco de dados, tente novamente!");
            }
            return new ResponseEntity(
                    new Retorno<ProdutoDTO>(
                        "Produto atualizado com sucesso!",
                            produtoDTO
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity buscarProdutoPeloId(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new RuntimeException("Id inválido!");
            }
            Produto produto = this.produtoRepository.findById(id)
                    .orElse(null);
            if (produto == null) {
                return new ResponseEntity("Não existe um produto com esse id!", HttpStatus.OK);
            }
            ProdutoDTO produtoDTO = new ProdutoDTO(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getQuantidadeUnidadesEstoque(),
                    produto.getPreco()
            );
            return new ResponseEntity(
                    new Retorno<ProdutoDTO>(
                            "Produto encontrado com sucesso!",
                            produtoDTO
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity buscarProdutosEntrePrecos(double precoInicial, double precoFinal) {
        try {
            if (precoInicial > precoFinal) {
                throw new RuntimeException("O preço inicial não deve ser maior que o preço final!");
            }
            List<Produto> produtos = this.produtoRepository.buscarProdutosEntrePrecos(precoInicial, precoFinal);
            if (produtos.size() == 0) {
                return new ResponseEntity("Não existem produtos entre esses preços!", HttpStatus.OK);
            }
            List<ProdutoDTO> produtoDTOS = new ArrayList<>();
            produtos.forEach((p) -> {
                produtoDTOS.add(new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getQuantidadeUnidadesEstoque(),
                        p.getPreco()
                ));
            });
            return new ResponseEntity(produtoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
