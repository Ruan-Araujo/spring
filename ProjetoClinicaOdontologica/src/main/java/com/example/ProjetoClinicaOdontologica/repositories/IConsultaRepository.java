package com.example.ProjetoClinicaOdontologica.repositories;

import com.example.ProjetoClinicaOdontologica.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {

}
