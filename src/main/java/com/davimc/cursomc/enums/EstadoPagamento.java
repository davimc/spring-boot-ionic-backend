package com.davimc.cursomc.enums;

public enum EstadoPagamento {

    PENDENTE(1,"Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricacao;

    EstadoPagamento(int cod, String descricacao) {
        this.cod = cod;
        this.descricacao = descricacao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricacao() {
        return descricacao;
    }

    public void setDescricacao(String descricacao) {
        this.descricacao = descricacao;
    }
    public static EstadoPagamento toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + id);
    }
}
