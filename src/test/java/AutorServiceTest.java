import org.example.service.Autor.AutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private AutorService autorService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    void deveRetornarPrintComNomeDoAutor() throws SQLException {
        String name = "Ana";
        when(statement.executeUpdate(anyString())).thenReturn(0);
        autorService.addAutor(name);
    }

    @Test
    void deletarAutorComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        autorService.deleteAutor(1);

    }

    @Test
    void deletarAutorComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        autorService.deleteAutor(1);
    }

    @Test
    void listarAutoresComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true, true, false); // Simula duas linhas no resultado
        when(resultSet.getInt("id")).thenReturn(1, 2); // Simula valores de ID
        when(resultSet.getString("name")).thenReturn("Roberta", "Luana"); // Simula nomes de autores
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        autorService.listarAutores();

    }

}