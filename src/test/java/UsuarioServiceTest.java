import org.example.service.Autor.AutorService;
import org.example.service.User.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void addUsuarioSucesso() throws SQLException {
        String name = "Ana";
        String email = "ana@gmail.com";
        int cpf = 12345;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        usuarioService.adicionarUsuario(name,email,cpf);
    }

    @Test
    void deletarUsuarioComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        usuarioService.deletarUsuario(1);

    }

    @Test
    void deletarUsuarioComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        usuarioService.deletarUsuario(1);
    }

    @Test
    public void AtualizarUsuarioComSucesso() throws SQLException {
        int cpf = 123456;
        when(statement.executeUpdate(anyString())).thenReturn(123456);
        usuarioService.atualizarUsuario(cpf, "ana@gmail.com");
    }
    @Test
    public void AtualizarUsuarioErrado() throws SQLException {
        int cpf = 157858;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        usuarioService.atualizarUsuario(cpf, "ana@gmail.com");
    }

    @Test
    void listarUsuariosComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false); // Simula duas linhas no resultado
        when(resultSet.getString("id")).thenReturn("1"); // Simula valores de ID
        when(resultSet.getInt("cpf")).thenReturn(123456); // Simula valores de ID
        when(resultSet.getString("name")).thenReturn("Ana"); // Simula valores de ID
        when(resultSet.getString("email")).thenReturn("ana@gmail.com");

        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        usuarioService.listarUsuarios();

    }
}
