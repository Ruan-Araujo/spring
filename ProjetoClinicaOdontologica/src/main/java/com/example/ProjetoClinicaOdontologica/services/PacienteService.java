package com.example.ProjetoClinicaOdontologica.services;

import com.example.ProjetoClinicaOdontologica.entities.Paciente;
import com.example.ProjetoClinicaOdontologica.repositories.IEnderecoRepository;
import com.example.ProjetoClinicaOdontologica.repositories.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements ICrudService<Paciente, Integer>{

   @Autowired
   private IPacienteRepository pacienteRepository;

   @Autowired
   private IEnderecoRepository enderecoRepository;


    @Override
    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void excluirId(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        paciente.setDataCadastro(new Date());
        return pacienteRepository.save(paciente);
    }
}
