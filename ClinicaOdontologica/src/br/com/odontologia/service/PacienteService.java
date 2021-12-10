package br.com.odontologia.service;

import br.com.odontologia.dao.IDao;
import br.com.odontologia.model.Paciente;

import java.util.List;
import java.util.Optional;

public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente salvar(Paciente paciente) {
        pacienteIDao.salvar(paciente);
        return paciente;
    }

    public Optional<Paciente> buscar(Integer id) {
        return  pacienteIDao.buscar(id);
    }

    public List<Paciente> buscarTodos() {
        return  pacienteIDao.buscarTodos();
    }

    public void excluir(Integer id) {
        pacienteIDao.excluir(id);
    }
}
