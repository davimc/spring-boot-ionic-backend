package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.dto.ClienteDTO;
import com.davimc.cursomc.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find (Long id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert (Cliente obj) {
        return repo.save(obj);
    }

    public Cliente update (Cliente obj) {
        Cliente newObj = find(obj.getId());
        newObj = updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(long id) {
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir o Cliente porque há entidades relacionadas");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(int page, int linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
    }

    private Cliente updateData(Cliente newObj,Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        return newObj;
    }
}
