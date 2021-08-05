package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.dto.CategoriaDTO;
import com.davimc.cursomc.repositories.CategoriaRepository;
import com.davimc.cursomc.services.exceptions.DataIntegrityException;
import com.davimc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(int page, int linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria insert (Categoria obj) {
        return repo.save(obj);
    }

    public Categoria update (Categoria obj) {
        Categoria newObj = find(obj.getId());
        newObj = updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(long id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma Categoria que tenha Produto(s) associado(s)");
        }
    }

    public Categoria fromDTO(CategoriaDTO objDTO) {
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }

    private Categoria updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
        return newObj;
    }
}
