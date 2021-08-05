package com.davimc.cursomc.resources;

import com.davimc.cursomc.domain.Produto;
import com.davimc.cursomc.dto.ProdutoDTO;
import com.davimc.cursomc.resources.utils.URL;
import com.davimc.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Long id){
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(name = "nome", defaultValue = "") String nome,
            @RequestParam(name = "categorias", defaultValue = "") String categorias,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction
    ) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Long> ids = URL.decodeLongList(categorias);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> produtos = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(produtos);
    }
}
