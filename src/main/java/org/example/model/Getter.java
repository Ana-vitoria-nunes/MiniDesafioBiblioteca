package org.example.model;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Getter {
    private Connection connection = Connect.fazerConexao();
    public String getLivroName(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT titulo FROM livro WHERE id = " + id);
            if (resultSet.next()) {
                return resultSet.getString("titulo");
            } else {
                return "Livro não encontrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao buscar nome do livro";
        }
    }
    public String getAutorName(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM autor WHERE id = " + id);
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return "Autor não encontrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao buscar nome do Autor";
        }
    }
    public int getUsuarioId(int cpf) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM usuario WHERE cpf = " + cpf);
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return Integer.parseInt("Usuario não encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Integer.parseInt("Erro ao buscar nome do Autor");
        }
    }
    public String getUserName(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM usuario WHERE id = " + id);
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return "Usuario não encontrado";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao buscar nome do Usuario";
        }
    }

}
