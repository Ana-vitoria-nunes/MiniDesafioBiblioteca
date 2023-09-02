package org.example.service.Emprestimo;

import org.example.model.Getter;

import java.sql.*;
import java.time.LocalDate;
import static org.example.connection.Connect.fazerConexao;

public class EmprestimoService {

    private Statement statement;
    ValidarEmprestimo validDataBaseService=new ValidarEmprestimo();
    Getter getter=new Getter();

    public EmprestimoService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void adicionarEmprestimo(int id_User, int id_Livro, Date data_emprestimo, Date data_devolucao) {
        if (!validDataBaseService.isValidUsuarioId(id_User)) {
            System.out.println("ID de usuário inválido!");
            return;
        }
        if (!validDataBaseService.isValidBookId(id_Livro)) {
            System.out.println("ID de livro inválido!");
            return;
        }
        if (validDataBaseService.isBookAlreadyLoaned(id_Livro)) {
            System.out.println("Este livro já está emprestado!");
            return;
        }

        String sql = "INSERT INTO emprestimo (id_User, id_Livro, data_emprestimo, data_devolucao) " +
                "VALUES (" + id_User + ", " + id_Livro + ", '" + data_emprestimo + "', '" + data_devolucao + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Empréstimo adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Empréstimo devolvido com sucesso!");
            } else {
                System.out.println("Empréstimo com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEmprestimo(int id, LocalDate data_devolucao) {
        String sql = "UPDATE emprestimo SET data_devolucao = '" + data_devolucao + "' " +
                "WHERE id = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Empréstimo com ID " + id + " atualizado com sucesso.");
            } else {
                System.out.println("Empréstimo com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarEmprestimos(int id_novo) {
        String sql = "SELECT id, id_User, id_Livro, data_emprestimo, data_devolucao FROM emprestimo WHERE id_User = " + id_novo;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_User = resultSet.getInt("id_User");
                int id_Livro = resultSet.getInt("id_Livro");
                LocalDate data_emprestimo = resultSet.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = resultSet.getDate("data_devolucao").toLocalDate();
                String livro=getter.getLivroName(id_Livro);
                String user=getter.getUserName(id_User);

                System.out.println("ID: " + id);
                System.out.println("Usuário: " + user);
                System.out.println("Livro: " + livro);
                System.out.println("Data de Empréstimo: " + data_emprestimo);
                System.out.println("Data de Devolução: " + data_devolucao);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
