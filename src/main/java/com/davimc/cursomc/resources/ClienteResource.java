package com.davimc.cursomc.resources;

import com.davimc.cursomc.domain.Categoria;
import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.dto.CategoriaDTO;
import com.davimc.cursomc.dto.ClienteDTO;
import com.davimc.cursomc.dto.ClienteNewDTO;
import com.davimc.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/email")
    public ResponseEntity<Cliente> findByEmail(@RequestParam(value = "value") String email) {
        Cliente obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
        Cliente obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable long id) {
        Cliente obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = service.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
