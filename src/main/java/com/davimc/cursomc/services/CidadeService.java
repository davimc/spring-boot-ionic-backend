package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Cidade;
import com.davimc.cursomc.domain.Estado;
import com.davimc.cursomc.dto.CidadeDTO;
import com.davimc.cursomc.repositories.CidadeRepository;
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
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;
    @Autowired
    private EstadoService estadoService;

    public Cidade find (Long id){
        Optional<Cidade> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

    public List<Cidade> findAll() {
        return repo.findAll();
    }

    public Page<Cidade> findPage(int page, int linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cidade insert (Cidade obj) {
        return repo.save(obj);
    }

    public Cidade update (Cidade obj) {
        Cidade newObj = find(obj.getId());
        newObj = updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(long id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma Cidade que tenha Produto(s) associado(s)");
        }
    }

    public Cidade fromDTO(CidadeDTO objDTO) {
        return new Cidade(objDTO.getId(),objDTO.getNome(), estadoService.find(objDTO.getEstadoId()));
    }

    private Cidade updateData(Cidade newObj, Cidade obj) {
        newObj.setNome(obj.getNome());
        return newObj;
    }
    public List<CidadeDTO> findCidades (Long estadoId) {
        return repo.findCidades(estadoId).stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
    }
}
