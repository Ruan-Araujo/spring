package com.example.ProjetoClinicaOdontologica.controllers;

import com.example.ProjetoClinicaOdontologica.entities.Dentista;
import com.example.ProjetoClinicaOdontologica.repositories.IDentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/db")
public class DbController {

    @Autowired
    private IDentistaRepository dentista;

    @GetMapping("/delete")
    public ResponseEntity<String> deleta() {
        dentista.deleteAll();
        return ResponseEntity.ok("{'message':'dados removidos'}");
    }

    @GetMapping("/seed")
    public ResponseEntity<String> popula() {
        dentista.deleteAll();

        Dentista fulano = new Dentista("Fulano", "da SIlva", "1234");

        Dentista ciclano = new Dentista("Ciclano", "cardoso", "12345");


        dentista.save(fulano);
        dentista.save(ciclano);


        return ResponseEntity.ok("{'message':'dados populadas'}");
    }
}
