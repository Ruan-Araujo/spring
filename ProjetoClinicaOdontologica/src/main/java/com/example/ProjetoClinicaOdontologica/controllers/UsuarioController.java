package com.example.ProjetoClinicaOdontologica.controllers;


import com.example.ProjetoClinicaOdontologica.entities.Usuario;
import com.example.ProjetoClinicaOdontologica.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.buscarId(id).orElse(null);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ResponseEntity<String> response = null;

        if (usuarioService.buscarId(id).isPresent()) {
            usuarioService.excluirId(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído com sucesso");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
        ResponseEntity<Usuario> response = null;

        if (usuario.getId() != null && usuarioService.buscarId(usuario.getId()).isPresent()){
            response = ResponseEntity.ok(usuarioService.atualizar(usuario));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
