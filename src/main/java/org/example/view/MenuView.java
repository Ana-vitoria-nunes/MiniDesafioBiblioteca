package org.example.view;
import org.example.model.InputUser;
import org.example.service.User.UsuarioService;
import java.util.Scanner;

public class MenuView {

    Scanner scanner = new Scanner(System.in);
    UsuarioService usuarioService = new UsuarioService();
    LogarUser logarUser=new LogarUser();
    LogarAdm logarAdm=new LogarAdm();
    InputUser inputUser=new InputUser();

    public void menu() {
        int opcao;
        do {
            System.out.println("\n0. Sair | 1. Login Usuario | 2. Cadastrar Usuario | 3. Login Admin");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> logarUser.fazerLogin();
                case 2 -> {
                    String novoNome = inputUser.readStringFromUser("Digite seu nome: ");
                    String novoEmail = inputUser.readStringFromUser("Digite seu email: ");
                    int novoCpf = inputUser.readIntFromUser("Digite seu CPF: ");
                    usuarioService.adicionarUsuario(novoNome, novoEmail, novoCpf);
                }
                case 3 -> logarAdm.fazerLogin();
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

}
