package com.example.ProjetoClinicaOdontologica.controllers;


import com.example.ProjetoClinicaOdontologica.entities.Endereco;
import com.example.ProjetoClinicaOdontologica.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Endereco> registrar(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.salvar(endereco));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscar(@PathVariable("id") Integer id) {
        Endereco endereco = enderecoService.buscarId(id).orElse(null);

        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ResponseEntity<String> response = null;

        if (enderecoService.buscarId(id).isPresent()) {
            enderecoService.excluirId(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído com sucesso");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        return ResponseEntity.ok(enderecoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Endereco> atualizar(@RequestBody Endereco endereco) {
        ResponseEntity<Endereco> response = null;

        if (endereco.getId() != null && enderecoService.buscarId(endereco.getId()).isPresent()){
            response = ResponseEntity.ok(enderecoService.atualizar(endereco));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
