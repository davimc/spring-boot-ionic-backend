package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Cidade;
import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.domain.Endereco;
import com.davimc.cursomc.domain.Produto;
import com.davimc.cursomc.domain.enums.TipoCliente;
import com.davimc.cursomc.dto.ClienteDTO;
import com.davimc.cursomc.dto.ClienteNewDTO;
import com.davimc.cursomc.repositories.ClienteRepository;
import com.davimc.cursomc.repositories.EnderecoRepository;
import com.davimc.cursomc.services.exceptions.DataIntegrityException;
import com.davimc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find (Long id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    @Transactional
    public Cliente insert (Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
            throw new DataIntegrityException("Não é possível excluir o Cliente porque há pedidos relacionados");
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
        return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(), null,null,null);
    }
    public Cliente fromDTO(ClienteNewDTO objDTO) {
        Cliente cli = new Cliente(null,objDTO.getNome(),objDTO.getEmail(), pe.encode(objDTO.getSenha()),objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        Cidade cid = new Cidade(objDTO.getCidadeId(), null,null);
        Endereco end = new Endereco(null, objDTO.getRua(), objDTO.getNum(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);

        cli.getEnderecos().add(end);
        cli.getTelefone().add(objDTO.getTelefone1());

        if(objDTO.getTelefone2() != null)
            cli.getTelefone().add(objDTO.getTelefone2());
        if(objDTO.getTelefone3() != null)
            cli.getTelefone().add(objDTO.getTelefone3());

        return cli;
    }

    private Cliente updateData(Cliente newObj,Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        return newObj;
    }

    public Cliente findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + email + ", Tipo: " + Cliente.class.getName()));
    }
}
