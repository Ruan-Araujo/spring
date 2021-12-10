package br.com.odontologia.dao.implementacao;
import org.apache.log4j.Logger;
import br.com.odontologia.dao.IDao;
import br.com.odontologia.dao.configuracao.ConfiguracaoJDBC;
import br.com.odontologia.model.Dentista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DentistaDaoH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public DentistaDaoH2() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Dentista salvar(Dentista dentista) {

        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;

        String query = String.format(
          "INSERT INTO dentista(numeroMatricula, nome, sobrenome) " +
          "VALUES ('%s', '%s', '%s')",

          dentista.getNumeroMatricula(),
          dentista.getNome(),
          dentista.getSobreNome()
        );

        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                dentista.setId(keys.getInt(1));
            }
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista;
    }

    @Override
    public Optional<Dentista> buscar(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;

        String query = String.format(
                "SELECT id, numeroMatricula, nome, sobrenome FROM dentista " +
                        "WHERE id = '%s'", id
        );

        Dentista dentista = null;

        try {
            statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(query);

            while (resultado.next()) {
                dentista = criarObjetoDentista(resultado);
            }

            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    @Override
    public void excluir(Integer id) {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;

        String query = String.format(
                "DELETE FROM dentista WHERE id = '%s'", id
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
    public List<Dentista> buscarTodos() {
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;

        String query = "SELECT * FROM dentista";

        List<Dentista> dentistas = new ArrayList<>();

        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                dentistas.add(criarObjetoDentista(resultado));
            }

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return dentistas;
    }

    private Dentista criarObjetoDentista(ResultSet resultado) throws SQLException {
        Integer idDentista = resultado.getInt("id");
        String numeroMatricula = resultado.getString("numeroMatricula");
        String nome = resultado.getString("nome");
        String sobrenome = resultado.getString("sobrenome");

        return new Dentista(idDentista, numeroMatricula, nome, sobrenome);
    }
}
