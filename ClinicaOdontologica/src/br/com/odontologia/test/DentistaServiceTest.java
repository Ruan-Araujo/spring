package br.com.odontologia.test;
import br.com.odontologia.dao.implementacao.DentistaDaoH2;
import br.com.odontologia.model.Dentista;
import br.com.odontologia.service.DentistaService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public class DentistaServiceTest {

    private static DentistaService dentistaService =
            new DentistaService(
                new DentistaDaoH2()
            );

    @BeforeClass
    public static void carregarDataSet() {
        Dentista dentista1 = dentistaService.salvar(
                new Dentista("2142412", "Ruan", "Araújo")
        );

        Dentista dentista2 = dentistaService.salvar(
                new Dentista("21424121", "José", "Camargo")
        );

        Dentista dentista3 = dentistaService.salvar(
                new Dentista("21424121", "José", "Camargo")
        );

        Dentista dentista4 = dentistaService.salvar(
                new Dentista("21424121", "José", "Camargo")
        );
    }

    @Test
    public void cadastrarEbuscarDentistaTest() {
        Dentista dentista5 = dentistaService.salvar(
                new Dentista("2313213", "dentista", "generico")
        );

        Assert.assertNotNull(dentistaService.buscar(dentista5.getId()));
    }

    @Test
    public void ecluirDentistasTest() {
        dentistaService.excluir(2);
        Assert.assertTrue(dentistaService.buscar(2).isEmpty());
    }

    @Test
    public void trazerTodos() {
        List<Dentista> dentistas = dentistaService.buscarTodos();
        System.out.println(dentistas);
    }
}
