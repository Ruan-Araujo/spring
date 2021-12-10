package com.example.ProjetoClinicaOdontologica.controllers;

import com.example.ProjetoClinicaOdontologica.entities.Dentista;
import com.example.ProjetoClinicaOdontologica.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dentista> registrarDentista(@RequestBody Dentista dentista) {
        return ResponseEntity.ok(dentistaService.salvar(dentista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscar(@PathVariable("id") Integer id) {
        Dentista dentista = dentistaService.buscarId(id).orElse(null);

        return ResponseEntity.ok(dentista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ResponseEntity<String> response = null;

        if (dentistaService.buscarId(id).isPresent()) {
            dentistaService.excluirId(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído com sucesso");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Dentista>> buscarTodos() {
        return ResponseEntity.ok(dentistaService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Dentista> atualizar(@RequestBody Dentista dentista) {
        ResponseEntity<Dentista> response = null;

        if (dentista.getId() != null && dentistaService.buscarId(dentista.getId()).isPresent()){
            response = ResponseEntity.ok(dentistaService.atualizar(dentista));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
