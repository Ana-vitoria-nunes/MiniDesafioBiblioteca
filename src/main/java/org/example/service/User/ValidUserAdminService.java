package org.example.service.User;

import org.example.connection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidUserAdminService {
    private  Connection connection = Connect.fazerConexao();

    private boolean isAdmin(String alias) {
        return alias.equals("Lorena");
    }

    public boolean isValidUserCredentials(String alias, int cpf) {

        if (isAdmin(alias)) {
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

                if (count > 0) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Credenciais de administrador inválidas.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao verificar as credenciais do usuário.");
            }
        } else {
            throw new IllegalArgumentException("Acesso não autorizado. Você não é um administrador.");
        }
    }
}
