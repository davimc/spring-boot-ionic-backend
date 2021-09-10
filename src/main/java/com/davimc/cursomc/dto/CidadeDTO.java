package com.davimc.cursomc.dto;

import com.davimc.cursomc.domain.Cidade;
import com.davimc.cursomc.domain.Estado;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    private Long estadoId;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade obj) {
        id = obj.getId();
        nome = obj.getNome();
        estadoId = obj.getEstado().getId();
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

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }
}
