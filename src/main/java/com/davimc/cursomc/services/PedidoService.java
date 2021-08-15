package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.*;
import com.davimc.cursomc.enums.EstadoPagamento;
import com.davimc.cursomc.repositories.ItemPedidoRepository;
import com.davimc.cursomc.repositories.PagamentoRepository;
import com.davimc.cursomc.repositories.PedidoRepository;
import com.davimc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;



    public Pedido find (Long id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        if(obj.getPagamento() instanceof PagamentoBoleto){
            PagamentoBoleto pgto = (PagamentoBoleto) obj.getPagamento();
            pgto.setDataPagamento(null);
            boletoService.preencherPagamentoBoleto(pgto,obj.getInstante());
        }

        repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for(ItemPedido ip : obj.getItems()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }
}
