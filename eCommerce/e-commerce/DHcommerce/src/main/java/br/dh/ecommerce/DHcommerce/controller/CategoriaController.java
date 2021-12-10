package br.dh.ecommerce.DHcommerce.controller;


import br.dh.ecommerce.DHcommerce.entity.Categoria;
import br.dh.ecommerce.DHcommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping()
    public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.salvar(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id).orElse(null));
    }

    @PutMapping
    public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria) {
        ResponseEntity<Categoria> response = null;
        if (categoria.getId() != null && categoriaService.buscarPorId(categoria.getId()).isPresent())
            response = ResponseEntity.ok(categoriaService.atualizar(categoria));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (categoriaService.buscarPorId(id).isPresent()) {
            categoriaService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoria excluído");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Iterable<Categoria>> buscarTodos() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }
}
