package br.dh.ecommerce.DHcommerce.controller;


import br.dh.ecommerce.DHcommerce.dto.ProdutoDto;
import br.dh.ecommerce.DHcommerce.entity.Produto;
import br.dh.ecommerce.DHcommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @PostMapping()
    public ResponseEntity<Produto> cadastrar(@RequestBody ProdutoDto produtoDto) {
        return ResponseEntity.ok(produtoService.salvar(produtoDto));
    }

    @GetMapping("/{nomeTitulo}")
    public ResponseEntity<List<Produto>> burcarPorTitulo(@PathVariable("nomeTitulo") String nomeTitulo) {
        return ResponseEntity.ok(produtoService.findByTitulo(nomeTitulo));
    }

    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody ProdutoDto produtoDto) {
        ResponseEntity<Produto> response = null;
        if (produtoDto.getCategoria() != null && produtoService.buscarPorId(produtoDto.getCategoria()).isPresent())
            response = ResponseEntity.ok(produtoService.atualizar(produtoDto));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (produtoService.buscarPorId(id).isPresent()) {
            produtoService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto excluído");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Iterable<Produto>> buscarTodos() {

        return ResponseEntity.ok(produtoService.buscarTodos());
    }
    @GetMapping("/categorias/{nomeCategoria}")
    public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable("nomeCategoria") String nomeCategoria) {
        return ResponseEntity.ok(produtoService.findByName(nomeCategoria));
    }



}
