package com.generation.games.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O atributo nome é obrigatório!")
    private String nome;

    @NotNull(message = "O atributo descrição é obrigatória!")
    private String descricao;

    @NotNull(message = "O atributo preço é obrigatório!")
    private float preco;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome() {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao() {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco() {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
   }
}
