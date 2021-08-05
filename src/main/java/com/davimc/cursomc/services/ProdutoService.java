package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.domain.Produto;
import com.davimc.cursomc.dto.ProdutoDTO;
import com.davimc.cursomc.repositories.CategoriaRepository;
import com.davimc.cursomc.repositories.ProdutoRepository;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find (Long id){
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Long> ids, int page, int linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }


    public Produto insert (Produto obj) {
        return repo.save(obj);
    }

    public Produto update (Produto obj) {
        Produto newObj = find(obj.getId());
        newObj = updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(long id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma Produto que tenha Produto(s) associado(s)");
        }
    }

    public List<Produto> findAll() {
        return repo.findAll();
    }


    public Produto fromDTO(ProdutoDTO objDTO) {
        throw new UnsupportedOperationException("Não implementado");
    }

    private Produto updateData(Produto newObj, Produto obj) {
        throw new UnsupportedOperationException("Não implementado");
    }
}
