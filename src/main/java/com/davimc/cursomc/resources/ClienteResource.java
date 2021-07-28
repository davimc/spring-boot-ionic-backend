package com.davimc.cursomc.resources;

import com.davimc.cursomc.services.CategoriaService;
import com.davimc.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Object obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    /*@PostMapping
    public ResponseEntity<?> create(String nome){

    }*/
}
