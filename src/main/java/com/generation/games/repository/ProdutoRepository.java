package com.generation.games.repository;

import com.generation.games.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
