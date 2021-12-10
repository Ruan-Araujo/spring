package com.example.ProjetoClinicaOdontologica.services;

import com.example.ProjetoClinicaOdontologica.entities.Consulta;
import com.example.ProjetoClinicaOdontologica.repositories.IConsultaRepository;
import com.example.ProjetoClinicaOdontologica.repositories.IDentistaRepository;
import com.example.ProjetoClinicaOdontologica.repositories.IPacienteRepository;
import com.example.ProjetoClinicaOdontologica.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService implements ICrudService<Consulta, Integer>{

    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IDentistaRepository dentistaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<Consulta> buscarId(Integer id) {
        return consultaRepository.findById(id);
    }

    @Override
    public List<Consulta> buscarTodos() {
        return consultaRepository.findAll();
    }

    @Override
    public void excluirId(Integer id) {
        consultaRepository.deleteById(id);
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }
}
