package com.example.ProjetoClinicaOdontologica.services;

import com.example.ProjetoClinicaOdontologica.entities.Dentista;
import com.example.ProjetoClinicaOdontologica.repositories.IDentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DentistaService implements ICrudService<Dentista, Integer>{

    @Autowired
    private IDentistaRepository dentistaRepository;

    @Override
    public Dentista salvar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    @Override
    public Optional<Dentista> buscarId(Integer id) {
        return dentistaRepository.findById(id);
    }

    @Override
    public List<Dentista> buscarTodos() {
        return dentistaRepository.findAll();
    }

    @Override
    public void excluirId(Integer id) {
        dentistaRepository.deleteById(id);
    }

    @Override
    public Dentista atualizar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }
}
