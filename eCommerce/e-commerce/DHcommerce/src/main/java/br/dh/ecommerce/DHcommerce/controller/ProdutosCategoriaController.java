package br.dh.ecommerce.DHcommerce.controller;


import br.dh.ecommerce.DHcommerce.entity.Categoria;
import br.dh.ecommerce.DHcommerce.entity.Produto;
import br.dh.ecommerce.DHcommerce.service.CategoriaService;
import br.dh.ecommerce.DHcommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
@RequestMapping("/produtos/categorias")
public class ProdutosCategoriaController {

    private CategoriaService categoriaService;
    private ProdutoService produtoService;

    @Autowired
    public ProdutosCategoriaController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<String>> buscarTodosNomes() {

        return ResponseEntity.ok(categoriaService.buscarTodosNomes());
    }


}
