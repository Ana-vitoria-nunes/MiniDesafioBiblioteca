package org.example.service.User;

import java.sql.*;
import static org.example.connection.Connect.fazerConexao;


public class UsuarioService {

    private Statement statement;

    public UsuarioService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void adicionarUsuario(String name, String email, int cpf) {
        String sql = "INSERT INTO usuario (name, email, cpf) VALUES ('" + name + "', '" + email + "', " + cpf + ")";
        try {
            statement.executeUpdate(sql);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarUsuario(int cpf, String email) {
        String sql = "UPDATE usuario SET  email = '" + email + "' WHERE cpf = " + cpf;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Usuário com CPF " + cpf + " atualizado com sucesso.");
            } else {
                System.out.println("Usuário com CPF " + cpf + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarUsuarios() {
        String sql = "SELECT id,cpf, name, email FROM usuario"; // Alterado para listar os campos da tabela "usuario"
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String id=resultSet.getString("id");
                int cpf = resultSet.getInt("cpf");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: "+ id +"CPF: " + cpf + " | Nome: " + name + " | Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

