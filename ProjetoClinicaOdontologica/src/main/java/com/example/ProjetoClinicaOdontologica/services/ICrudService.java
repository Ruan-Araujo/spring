package com.example.ProjetoClinicaOdontologica.services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T, R>{

    T salvar(T t);
    Optional<T> buscarId(R id);
    List<T> buscarTodos();
    void excluirId(R id);
    public T atualizar(T t);
}
