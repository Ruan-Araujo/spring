package com.example.ProjetoClinicaOdontologica.services;

import com.example.ProjetoClinicaOdontologica.entities.Usuario;
import com.example.ProjetoClinicaOdontologica.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService implements ICrudService<Usuario, Integer>{

    public UsuarioService() {
    }

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public void excluirId(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
