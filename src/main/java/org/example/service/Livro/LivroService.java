package org.example.service.Livro;

import org.example.model.Getter;

import java.sql.*;
import static org.example.connection.Connect.fazerConexao;

public class LivroService {

    private Statement statement;
    ValidarLivro validarLivro=new ValidarLivro();
    Getter getter=new Getter();
    public LivroService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarLivro(int isbn, String titulo, int id_autor) {
        if (!validarLivro.isValidAutorId(id_autor)) {
            System.out.println("ID de autor inválido!");
            return;
        }
        String sql = "INSERT INTO livro (isbn, titulo, id_autor) VALUES (" + isbn + ", '" + titulo + "', " + id_autor + ")";
        try {
            statement.executeUpdate(sql);
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarLivro(int id) {
        String sql = "DELETE FROM livro WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarLivros() {
        String sql = "SELECT id, isbn, titulo, id_autor FROM livro";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int isbn = resultSet.getInt("isbn");
                String titulo = resultSet.getString("titulo");
                int id_autor = resultSet.getInt("id_autor");
                String autor=getter.getAutorName(id_autor);

                System.out.println("ID : " + id + " | ISBN: " + isbn+ " | Título: " + titulo+ " | Autor: " + autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
