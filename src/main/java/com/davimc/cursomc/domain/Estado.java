package com.davimc.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy="estado")
    private List<Cidade> cidades = new ArrayList<>();

    public Estado() {
    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<com.davimc.cursomc.domain.Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<com.davimc.cursomc.domain.Cidade> cidades) {
        this.cidades = cidades;
    }
}
