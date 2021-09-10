package com.davimc.cursomc.resources;

import com.davimc.cursomc.domain.Estado;
import com.davimc.cursomc.dto.CidadeDTO;
import com.davimc.cursomc.dto.EstadoDTO;
import com.davimc.cursomc.services.CidadeService;
import com.davimc.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<Estado> find(@PathVariable Long id) {
        Estado obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<EstadoDTO> estados = service.findAll().stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(estados);
    }

    @GetMapping("/{estado_id}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable("estado_id") Long estadoId) {
        List<CidadeDTO> cidades = cidadeService.findCidades(estadoId);
        return ResponseEntity.ok().body(cidades);
    }

    /*@GetMapping("/page")
    public ResponseEntity<Page<EstadoDTO>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction
    ) {
        Page<EstadoDTO> estados = service.findPage(page, linesPerPage, orderBy, direction).map(obj -> new EstadoDTO(obj));
        return ResponseEntity.ok().body(estados);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody EstadoDTO objDTO) {
        Estado obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody EstadoDTO objDTO, @PathVariable long id) {
        Estado obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }*/

}
