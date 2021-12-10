package br.com.odontologia.dao.implementacao;

import br.com.odontologia.dao.IDao;
import br.com.odontologia.dao.configuracao.ConfiguracaoJDBC;
import br.com.odontologia.model.Endereco;
import br.com.odontologia.model.Paciente;
import br.com.odontologia.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

public class PacienteDaoH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;
    private EnderecoDaoH2 enderecoDaoH2;

    public PacienteDaoH2(EnderecoDaoH2 enderecoDaoH2) {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
        this.enderecoDaoH2 = enderecoDaoH2;
    }

    @Override
    public Paciente salvar(Paciente paciente) {

        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        paciente.setEndereco(enderecoDaoH2.salvar(paciente.getEndereco()));

        String query = String.format(
                "INSERT INTO paciente (nome, sobrenome, rg, data_Cadastro, endereco_id)" +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')",

                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getRg(),
                Util.dateToTimeStamp(paciente.getDataCadastro()),
                paciente.getEndereco().getId()
        );

        try {

            statement = conexao.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                paciente.setId(keys.getInt(1));
            }
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {

        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format(
                "SELECT id, nome, sobrenome, rg, data_cadastro, endereco_id " +
                        "FROM paciente " + "WHERE id = '%s'", id
        );

        Paciente paciente = null;

        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                paciente = criarObjetoPaciente(resultado);
            }

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;

        String query = String.format(
                "DELETE FROM paciente WHERE id = '%s'", id
        );

        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;

        String query = "SELECT * FROM paciente";

        List<Paciente> pacientes = new ArrayList<>();

        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                pacientes.add(criarObjetoPaciente(resultado));
            }

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    private Paciente criarObjetoPaciente(ResultSet resultado) throws SQLException {
        Integer idPaciente = resultado.getInt("id");
        String nome = resultado.getString("nome");
        String sobrenome = resultado.getString("sobrenome");
        String rg = resultado.getString("rg");
        Date dataCadastro = resultado.getDate("data_Cadastro");
        Endereco endereco = enderecoDaoH2.buscar(
                resultado.getInt("endereco_id")).orElse(null);

        return new Paciente(idPaciente, nome, sobrenome, rg, dataCadastro, endereco);
    }
}
