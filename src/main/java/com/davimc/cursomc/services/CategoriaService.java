package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find (Long id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
