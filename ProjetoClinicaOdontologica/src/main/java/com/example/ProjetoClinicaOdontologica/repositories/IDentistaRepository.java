package com.example.ProjetoClinicaOdontologica.repositories;

import com.example.ProjetoClinicaOdontologica.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDentistaRepository extends JpaRepository<Dentista, Integer> {

}
