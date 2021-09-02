package com.davimc.cursomc.resources;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.domain.Pedido;
import com.davimc.cursomc.dto.CategoriaDTO;
import com.davimc.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Long id){
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(name = "orderBy", defaultValue = "instante") String orderBy,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction
    ) {
        Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
