package org.example.model;

import java.sql.Date;
import java.time.LocalDate;

public class EmprestimoModel {
    private int id_User;
    private int id_Livro;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public int getId_Livro() {
        return id_Livro;
    }

    public void setId_Livro(int id_Livro) {
        this.id_Livro = id_Livro;
    }

    public Date getData_emprestimo() {
        return Date.valueOf(data_emprestimo);
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return Date.valueOf(data_devolucao);
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
