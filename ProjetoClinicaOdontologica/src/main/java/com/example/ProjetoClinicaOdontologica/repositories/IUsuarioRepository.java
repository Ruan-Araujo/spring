package com.example.ProjetoClinicaOdontologica.repositories;

import com.example.ProjetoClinicaOdontologica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
