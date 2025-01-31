package com.generation.games.repository;

import com.generation.games.model.Categoria;
import com.generation.games.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Produto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
