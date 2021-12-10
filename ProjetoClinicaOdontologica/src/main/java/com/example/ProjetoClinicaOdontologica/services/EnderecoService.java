package com.example.ProjetoClinicaOdontologica.services;

import com.example.ProjetoClinicaOdontologica.entities.Endereco;
import com.example.ProjetoClinicaOdontologica.repositories.IEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService implements ICrudService<Endereco, Integer>{

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Override
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Optional<Endereco> buscarId(Integer id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public void excluirId(Integer id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
