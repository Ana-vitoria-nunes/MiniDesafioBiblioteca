package org.example.service.Autor;
import java.sql.*;
import static org.example.connection.Connect.fazerConexao;
public class AutorService {

    private Statement statement;

    public AutorService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAutor(String name) {
        String sql = "INSERT INTO autor (name) VALUES ('" + name + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Autor " + name + " adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAutor(int id) {
        String sql = "DELETE FROM autor WHERE id=" + id;

        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Autor deletado com sucesso!");
            } else {
                System.out.println("Autor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarAutores() {
        String sql = "SELECT id, name FROM autor";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID do Autor: " + id + " | Nome: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAutor(int id, String name) {
        String sql = "UPDATE autor SET name='" + name + "' WHERE id=" + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Autor com ID " + id + " atualizado com sucesso.");
            } else {
                System.out.println("Autor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
