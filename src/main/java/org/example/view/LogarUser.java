package org.example.view;

import org.example.model.EmprestimoModel;
import org.example.model.Getter;
import org.example.model.InputUser;
import org.example.model.UsuarioModel;
import org.example.service.Emprestimo.EmprestimoService;
import org.example.service.User.UsuarioService;
import org.example.service.User.ValidarUser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogarUser {
    ValidarUser validDataBase=new ValidarUser();
    InputUser inputUser=new InputUser();
    EmprestimoModel emprestimoModel=new EmprestimoModel();
    EmprestimoService emprestimoService=new EmprestimoService();
    InputUser inputUserModel=new InputUser();
    UsuarioService usuarioService=new UsuarioService();
    UsuarioModel usuarioModel=new UsuarioModel();
    Getter getter=new Getter();
    public void fazerLogin() {
        String name = inputUserModel.readStringFromUser("Digite seu nome:");
        int password = inputUserModel.readIntFromUser("Digite seu cpf sem os carcteres:");

        if (validDataBase.isValidUserCredentials(name, password)) {
            System.out.println("========== Bem-Vindo(a) " + name + " ==========");
            validDataBase.userInfoByAlias(name);
            int option;
            do {
                menu();
                option = inputUserModel.readIntFromUser("Qual opção você deseja: ");
                switch (option) {
                    case 0 -> new MenuView();
                    case 1 -> addEmprestimo();
                    case 2 -> {
                        System.out.println("=====Meus emprestimos=====");
                        int id=getter.getUsuarioId(password);
                        emprestimoService.listarEmprestimos(id);

                    }
                    case 3 -> {
                        int id=getter.getUsuarioId(password);
                        emprestimoService.listarEmprestimos(id);
                        updateEmprestimo();
                    }
                    case 4 -> {
                        int id=getter.getUsuarioId(password);
                        emprestimoService.listarEmprestimos(id);
                        deleteEmprestimo();
                    }
                    case 5 -> update();
                    default -> System.out.println("Opção inválida, tente novamente!");
                }
            } while (option != 0);
        } else {
            System.out.println("Senha ou nome inválidos!");
        }
    }
    private void menu() {

        System.out.println("""
                
                0. Menu Principal |\s
                1. Fazer emprestimo |\s
                2. Ver meus emprestimos |\s
                3. Modificar emprestimo |\s
                4. Devolver emprestimo |\s
                5. Atualizar dados"""
        );
    }

    private void addEmprestimo()  {
        int id_user = inputUser.readIntFromUser("Qual seu id de usuario: ");
        int id_Livro = inputUser.readIntFromUser("Qual o id do livro: ");
        String data = inputUser.readStringFromUser("Qual a data da devolução(dd/MM/yyyy): ");

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDevolucao = LocalDate.parse(data, formatoData);
        LocalDate dataAtual = LocalDate.now();


        emprestimoModel.setId_User(id_user);
        emprestimoModel.setId_Livro(id_Livro);
        emprestimoModel.setData_emprestimo(dataAtual);
        emprestimoModel.setData_devolucao(dataDevolucao);

        emprestimoService.adicionarEmprestimo(emprestimoModel.getId_User(),emprestimoModel.getId_Livro(),
                emprestimoModel.getData_emprestimo(),emprestimoModel.getData_devolucao());
    }
    private void updateEmprestimo() {
        int id = inputUser.readIntFromUser("Qual o id do emprestimo: ");
        String nova_data = inputUser.readStringFromUser("Qual a nova data da devolução(dd/MM/yyyy):");
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDevolucao = LocalDate.parse(nova_data, formatoData);

        emprestimoModel.setData_devolucao(dataDevolucao);
        emprestimoService.atualizarEmprestimo(id, emprestimoModel.getData_devolucao().toLocalDate());

    }
    private void deleteEmprestimo() {
        int id = inputUser.readIntFromUser("Qual o ID do emprestimo que deseja deletar: ");
        emprestimoService.deletarEmprestimo(id);
    }
    private void update() {
        int cpf = inputUser.readIntFromUser("Qual seu cpf: ");
        String email = inputUser.readStringFromUser("Qual o novo email: ");
        usuarioModel.setEmail(email);
        usuarioModel.setCpf(cpf);
        usuarioService.atualizarUsuario(usuarioModel.getCpf(), usuarioModel.getEmail());
    }

}
