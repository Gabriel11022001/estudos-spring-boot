package com.gabrielsantos.app.repository;

import com.gabrielsantos.app.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where p.preco = :preco")
    List<Produto> buscarProdutosPeloPreco(@Param("preco") Double preco);
    @Query("select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal")
    List<Produto> buscarProdutosEntrePrecos(@Param("precoInicial") Double precoInicial, @Param("precoFinal") Double precoFinal);
}
