package br.com.odontologia.test;
import br.com.odontologia.model.Endereco;
import br.com.odontologia.model.Paciente;
import org.junit.*;

import br.com.odontologia.dao.implementacao.EnderecoDaoH2;
import br.com.odontologia.dao.implementacao.PacienteDaoH2;
import br.com.odontologia.service.EnderecoService;
import br.com.odontologia.service.PacienteService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;


import java.util.List;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Especifica a ordem em que eu quero que os testes rodem
@RunWith(JUnit4.class)
public class PacienteServiceTest {

    private static PacienteService pacienteService =
            new PacienteService(
                    new PacienteDaoH2(
                            new EnderecoDaoH2()
                    )
            );

    private EnderecoService enderecoService =
            new EnderecoService(
                    new EnderecoDaoH2()
            );

    @BeforeClass
    public static void carregarDataSet() {
        Endereco end1 =
                new Endereco("Av. Brasil", "325", "Centro", "Pelotas");
        Paciente p1 = pacienteService.salvar(
                new Paciente(
                        "Carla", "Santos", "2165784523", new Date(), end1));

        Endereco end2 =
                new Endereco("Rua Santos", "255", "Lapa", "Rio de Janeiro");
        Paciente p2 = pacienteService.salvar(
                new Paciente(
                        "Murilo", "Moreira", "6521447788",new Date(), end2));

        Endereco end3 =
                new Endereco("Av. do Forte", "74", "Jardim das Hortências", "São José");
        Paciente p3 = pacienteService.salvar(
                new Paciente(
                        "Manoel", "Albano", "6485002236",  new Date(), end3));

    }

    @AfterClass
    public static void depois() {
        System.out.println("Teste realizado com sucesso!");
    }

    @Test
    public void cadastrarEBuscarPacienteTest() {
        Endereco end4 =
                new Endereco("Av. Bento Martins", "866", "Centro", "Porto Alegre");
        Paciente p4 = pacienteService.salvar(
                new Paciente(
                        "Richard", "Silveira", "5411778541",  new Date(), end4));
        Assert.assertNotNull(pacienteService.buscar(p4.getId()));
    }

    @Test
    public void excluirPacienteTest() {
        pacienteService.excluir(3);
        Assert.assertTrue(pacienteService.buscar(3).isEmpty());
    }

    @Test
    public void trazerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        System.out.println(pacientes);
    }
}

