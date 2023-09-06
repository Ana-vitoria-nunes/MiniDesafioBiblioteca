import org.example.service.Emprestimo.EmprestimoService;
import org.example.service.Emprestimo.ValidarEmprestimo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.*;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private EmprestimoService emprestimoService;
    @InjectMocks
    private ValidarEmprestimo validDataBaseService = Mockito.mock(ValidarEmprestimo.class);


//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }


    @Test
    void testAdicionarEmprestimo_UsuarioInvalido() {
        // Configura o comportamento simulado do validDataBaseService
        when(validDataBaseService.isValidUsuarioId(anyInt())).thenReturn(true);

        LocalDate dataEmprestimo = LocalDate.of(2023, 9, 25);
        LocalDate dataDevolucao = LocalDate.of(2023, 9, 25);
        // Chama o método com um ID de usuário inválido
        emprestimoService.adicionarEmprestimo(1, 2, java.sql.Date.valueOf(dataEmprestimo), java.sql.Date.valueOf(dataDevolucao));
    }
    @Test
    void testAdicionarEmprestimo_LivroInvalido() {
        // Configura o comportamento simulado do validDataBaseService
        when(validDataBaseService.isValidUsuarioId(anyInt())).thenReturn(true);
        when(validDataBaseService.isValidBookId(anyInt())).thenReturn(false);

        LocalDate dataEmprestimo = LocalDate.of(2023, 9, 25);
        LocalDate dataDevolucao = LocalDate.of(2023, 9, 25);
        // Chama o método com um ID de livro inválido
        emprestimoService.adicionarEmprestimo(1, 2,java.sql.Date.valueOf(dataEmprestimo), java.sql.Date.valueOf(dataDevolucao));

    }

    @Test
    void testAdicionarEmprestimo_LivroJaEmprestado() {
        // Configura o comportamento simulado do validDataBaseService
        when(validDataBaseService.isValidUsuarioId(anyInt())).thenReturn(true);
        when(validDataBaseService.isValidBookId(anyInt())).thenReturn(true);
        when(validDataBaseService.isBookAlreadyLoaned(anyInt())).thenReturn(true);
        LocalDate dataEmprestimo = LocalDate.of(2023, 9, 25);
        LocalDate dataDevolucao = LocalDate.of(2023, 9, 25);

        // Chama o método com um livro já emprestado
        emprestimoService.adicionarEmprestimo(1, 2, java.sql.Date.valueOf(dataEmprestimo), java.sql.Date.valueOf(dataDevolucao));
    }

    @Test
    void testAdicionarEmprestimo_ComSucesso() throws SQLException {
        // Configura o comportamento simulado do validDataBaseService
        when(validDataBaseService.isValidUsuarioId(anyInt())).thenReturn(true);
        when(validDataBaseService.isValidBookId(anyInt())).thenReturn(true);
        when(validDataBaseService.isBookAlreadyLoaned(anyInt())).thenReturn(false);

        LocalDate dataEmprestimo = LocalDate.of(2023, 9, 25);
        LocalDate dataDevolucao = LocalDate.of(2023, 9, 25);
        // Configura o comportamento simulado para o statement.executeUpdate
        when(statement.executeUpdate(anyString())).thenReturn(1); // Supondo que a inserção foi bem-sucedida
        // Chama o método com dados válidos
        emprestimoService.adicionarEmprestimo(1, 2, java.sql.Date.valueOf(dataEmprestimo), java.sql.Date.valueOf(dataDevolucao));

    }
    @Test
    void deletarEmprestimoComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        emprestimoService.deletarEmprestimo(1);

    }

    @Test
    void deletarAutorComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        emprestimoService.deletarEmprestimo(1);
    }

    @Test
    public void AtualizarEmprestimoComSucesso() throws SQLException {
        int idEmprestimo = 1;
        LocalDate novaDataDevolucao = LocalDate.of(2023, 9, 25);
        when(statement.executeUpdate(anyString())).thenReturn(1);
        emprestimoService.atualizarEmprestimo(idEmprestimo, novaDataDevolucao);
    }
    @Test
    public void AtualizarEmprestimoErrado() throws SQLException {
        int idEmprestimo = 1;
        LocalDate novaDataDevolucao = LocalDate.of(2023, 9, 25);
        when(statement.executeUpdate(anyString())).thenReturn(0);
        emprestimoService.atualizarEmprestimo(idEmprestimo, novaDataDevolucao);
    }

    @Test
    void listarEmprestimosComSucesso() throws SQLException {
        int idUsuario= 1;
        LocalDate localDate = LocalDate.of(2023, 9, 25);
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false); // Simula duas linhas no resultado
        when(resultSet.getInt("id")).thenReturn(1); // Simula valores de ID
        when(resultSet.getInt("id_User")).thenReturn(2); // Simula valores de ID
        when(resultSet.getInt("id_Livro")).thenReturn(1); // Simula valores de ID
        when(resultSet.getDate("data_emprestimo")).thenReturn(java.sql.Date.valueOf(localDate));
        when(resultSet.getDate("data_devolucao")).thenReturn(java.sql.Date.valueOf(localDate));
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        emprestimoService.listarEmprestimos(idUsuario);

    }

}