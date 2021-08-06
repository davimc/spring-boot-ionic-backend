package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoBoleto(PagamentoBoleto pgto, Date instantePedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instantePedido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pgto.setDataVencimento(cal.getTime());
    }
}
