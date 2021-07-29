package com.davimc.cursomc.domain;

import com.davimc.cursomc.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoCartao extends Pagamento {
    private int numeroParcelas;

    public PagamentoCartao() {
    }

    public PagamentoCartao(EstadoPagamento estadoPagamento, Pedido pedido) {
        super(estadoPagamento, pedido);
    }

    public PagamentoCartao(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public PagamentoCartao(EstadoPagamento estadoPagamento, Pedido pedido, int numeroParcelas) {
        super(estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
