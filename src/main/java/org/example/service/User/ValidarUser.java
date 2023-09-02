package org.example.service.User;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidarUser {
    private Connection connection = Connect.fazerConexao();
    public boolean isValidUserCredentials(String alias,int cpf) {

        String sql = "SELECT COUNT(*) FROM usuario WHERE name=? AND cpf=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alias);
            preparedStatement.setInt(2, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            preparedStatement.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void userInfoByAlias(String alias) {
        String sql = "SELECT id, cpf, email FROM usuario WHERE name=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alias);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                System.out.println("Informações da Conta:\n ID: " + id + " | CPF: " + cpf + " | Nome: " + alias + " | Email: " + email);
            } else {
                System.out.println("Usuário com o nome " + alias + " não encontrado.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
