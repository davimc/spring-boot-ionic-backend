package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.repositories.CategoriaRepository;
import com.davimc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find (Long id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        return repo.save(obj);
    }
}
