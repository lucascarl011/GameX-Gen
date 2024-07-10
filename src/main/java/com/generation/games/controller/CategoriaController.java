package com.generation.games.controller;

import com.generation.games.model.Categoria;
import com.generation.games.model.Produto;
import com.generation.games.repository.CategoriaRepository;
import com.generation.games.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(categoriaRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        if (categoria.getProduto() != null && categoria.getProduto().stream().allMatch(produto -> produtoRepository.existsById(produto.getId())))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoriaRepository.save(categoria));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe!", null);
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
        if (categoriaRepository.existsById(categoria.getId())) {
            if (categoria.getProduto() != null && categoria.getProduto().stream().allMatch(produto -> produtoRepository.existsById(produto.getId())))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(categoriaRepository.save(categoria));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe!", null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaRepository.deleteById(id);
    }
}
