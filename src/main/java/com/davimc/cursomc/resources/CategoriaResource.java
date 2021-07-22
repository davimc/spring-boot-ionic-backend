package com.davimc.cursomc.resources;

import com.davimc.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Object obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    /*@PostMapping
    public ResponseEntity<?> create(String nome){

    }*/
}
