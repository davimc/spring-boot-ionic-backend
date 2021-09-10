package com.davimc.cursomc.dto;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.domain.Estado;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
