import org.example.service.Emprestimo.EmprestimoService;
import org.example.service.Emprestimo.ValidarEmprestimo;
import org.example.service.Livro.LivroService;
import org.example.service.Livro.ValidarLivro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private LivroService livroService;
    @InjectMocks
    private ValidarLivro validarLivro = Mockito.mock(ValidarLivro.class);

    @Test
    void testAdicionarLivro_IdAutorInvalido() {
        // Configura o comportamento simulado do validDataBaseService
        when(validarLivro.isValidAutorId(anyInt())).thenReturn(false);
        livroService.adicionarLivro(155, "Branca de neve",1);
    }
    @Test
    void testAdicionarLivro_Sucesso() {
        // Configura o comportamento simulado do validDataBaseService
        when(validarLivro.isValidAutorId(anyInt())).thenReturn(true);
        livroService.adicionarLivro(155, "Branca de neve",1);
    }

    @Test
    void deletarLivroComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        livroService.deletarLivro(1);

    }

    @Test
    void deletarLivroComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        livroService.deletarLivro(1);
    }

    @Test
    void listarLivrosComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false); // Simula duas linhas no resultado
        when(resultSet.getInt("id")).thenReturn(1); // Simula valores de ID
        when(resultSet.getInt("isbn")).thenReturn(155); // Simula valores de ID
        when(resultSet.getString("titulo")).thenReturn("Branca de neve"); // Simula nomes de autores
        when(resultSet.getInt("id_autor")).thenReturn(2);
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        livroService.listarLivros();

    }
}
