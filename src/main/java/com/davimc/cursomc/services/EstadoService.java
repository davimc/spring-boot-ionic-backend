package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Estado;
import com.davimc.cursomc.dto.EstadoDTO;
import com.davimc.cursomc.repositories.EstadoRepository;
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
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public Estado find (Long id){
        Optional<Estado> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }

    public List<Estado> findAll() {
        return repo.findAllByOrderByNome();
    }

    /*public Page<Estado> findPage(int page, int linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Estado insert (Estado obj) {
        return repo.save(obj);
    }

    public Estado update (Estado obj) {
        Estado newObj = find(obj.getId());
        newObj = updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(long id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma Estado que tenha Produto(s) associado(s)");
        }
    }

    public Estado fromDTO(EstadoDTO objDTO) {
        return new Estado(objDTO.getId(),objDTO.getNome());
    }

    private Estado updateData(Estado newObj, Estado obj) {
        newObj.setNome(obj.getNome());
        return newObj;
    }*/
}
