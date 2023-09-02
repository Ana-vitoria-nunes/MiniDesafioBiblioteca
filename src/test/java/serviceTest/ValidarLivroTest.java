package serviceTest;

import org.example.service.Livro.ValidarLivro;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ValidarLivroTest {

    @InjectMocks
    private ValidarLivro validarLivro;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Configurar o comportamento dos mocks
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Definir o comportamento padrão do resultSet
        when(resultSet.next()).thenReturn(true); // Simular que há um resultado válido
        when(resultSet.getInt(1)).thenReturn(1); // Simular que há um registro

        // Configurar um caso de teste onde não há resultado válido
        when(resultSet.next()).thenReturn(false);
    }

    @Test
    public void testIsValidAutorId_ValidId() {
        assertTrue(validarLivro.isValidAutorId(1)); // ID válido com resultado no banco de dados
    }

    @Test
    public void testIsValidAutorId_InvalidId() {
        assertFalse(validarLivro.isValidAutorId(62)); // ID inválido, sem resultado no banco de dados
    }
}

