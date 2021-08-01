package com.davimc.cursomc.domain;

import com.davimc.cursomc.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoCartao extends Pagamento {
    private int numeroParcelas;

    public PagamentoCartao() {
    }

    public PagamentoCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, int numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
