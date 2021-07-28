package com.davimc.cursomc.enums;

public enum TipoCliente {
    PESSOAJURIDICA(1, "Pessoa Jurídica"),
    PESSOAFISICA(2, "Pessoa Física");

    private int cod;
    private String descricacao;

    TipoCliente(int cod, String descricacao) {
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
    public static TipoCliente toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (TipoCliente x : TipoCliente.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + id);
    }
}
