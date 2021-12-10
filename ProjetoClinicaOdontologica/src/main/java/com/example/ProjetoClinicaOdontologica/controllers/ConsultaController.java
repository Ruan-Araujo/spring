package com.example.ProjetoClinicaOdontologica.controllers;

import com.example.ProjetoClinicaOdontologica.entities.Consulta;
import com.example.ProjetoClinicaOdontologica.services.ConsultaService;
import com.example.ProjetoClinicaOdontologica.services.DentistaService;
import com.example.ProjetoClinicaOdontologica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Consulta> registrar(@RequestBody Consulta consulta) {
        ResponseEntity<Consulta> response = null;

        if (pacienteService.buscarId(consulta.getPaciente().getId()).isPresent() &&
        dentistaService.buscarId(consulta.getDentista().getId()).isPresent()) {
            response = ResponseEntity.ok(consultaService.salvar(consulta));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> Listar() {
        return ResponseEntity.ok(consultaService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ResponseEntity<String> response = null;

        if (consultaService.buscarId(id).isPresent()) {
            consultaService.excluirId(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído com sucesso");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping
    public ResponseEntity<Consulta> atualizar(@RequestBody Consulta consulta) {
        ResponseEntity<Consulta> response = null;

        if (consulta.getId() != null && consultaService.buscarId(consulta.getId()).isPresent()){
            response = ResponseEntity.ok(consultaService.atualizar(consulta));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
