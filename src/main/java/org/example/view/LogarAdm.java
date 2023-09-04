package org.example.view;

import org.example.model.AutorModel;
import org.example.model.InputUser;
import org.example.model.LivroModel;
import org.example.model.UsuarioModel;
import org.example.service.Autor.AutorService;
import org.example.service.Livro.LivroService;
import org.example.service.User.UsuarioService;
import org.example.service.User.ValidUserAdminService;
import org.example.service.User.ValidarUser;

import java.util.Scanner;

public class LogarAdm {

    Scanner scanner = new Scanner(System.in);
    UsuarioModel usuarioModel = new UsuarioModel();
    UsuarioService usuarioService = new UsuarioService();
    AutorModel autorModel = new AutorModel();
    AutorService autorService = new AutorService();
    ValidUserAdminService validUserAdmin = new ValidUserAdminService();
    ValidarUser validDataBase=new ValidarUser();
    InputUser inputUser = new InputUser();
    LivroModel livroModel = new LivroModel();
    LivroService livroService = new LivroService();


    private void menuAdm() {
        System.out.println("""
                
                0. Menu Principal |\s
                1. Verificar funções livro |\s
                2. Verificar funções autor|\s
                3. Deletar usuario |\s
                4. Atualizar dados de login |\s
                """
        );
    }

    private void menuLivro() {
        System.out.println("\n=====Bem vindo as opções do Livro=====");
        System.out.println("""
                
                0. Voltar para menu ADM \s
                1. Inserir Livro \s
                2. Deletar livro \s
                3. Ver Livros disponiveis |\s
                """
        );
    }

    public void caseLivro() {
        int option;
        do {
            menuLivro();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");
            switch (option) {
                case 0 -> menuAdm();
                case 1 -> addLivro();
                case 2 -> deleteBook();
                case 3 ->livroService.listarLivros();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }

    private void menuAutor() {
        System.out.println("\n=====Bem vindo as opções do Autor=====");
        System.out.println("""
                0. Voltar para menu ADM \s
                1. Inserir Autor |\s
                2. Deletar Autor |\s
                3. Ver Autores disponiveis |\s
                """
        );
    }

    public void caseAutor() {
        int option;
        do {
            menuAutor();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> menuAdm();
                case 1 -> addAutor();
                case 2 -> deleteAutor();
                case 3 -> autorService.listarAutores();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }

    public void fazerLogin() {
        try {
            String nome = inputUser.readStringFromUser("Digite seu nome:");
            int senha =inputUser.readIntFromUser("Digite seu cpf (sem os caracteres especiais):");
            usuarioModel.setName(nome);
            usuarioModel.setCpf(senha);


            if (validUserAdmin.isValidUserCredentials(usuarioModel.getName(), usuarioModel.getCpf())) {
                System.out.println("========== Bem-Vindo(a) " + usuarioModel.getName() + " ==========");
                validDataBase.userInfoByAlias(nome);
                int option;
                do {
                    menuAdm();
                    option = inputUser.readIntFromUser("Qual opção você deseja: ");

                    switch (option) {
                        case 0 -> new MenuView();
                        case 1 -> {
                            menuLivro();
                            caseLivro();
                        }
                        case 2 -> {
                            menuAutor();
                            caseAutor();
                        }
                        case 3 -> deleteUsuario();
                        case 4 -> update();
                        default -> System.out.println("Opção inválida, tente novamente!");
                    }
                } while (option != 0);
            } else {
                System.out.println("Senha ou nome inválidos!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void addLivro() {
        autorService.listarAutores();
        int isbn = inputUser.readIntFromUser("Qual o isbn do livro: ");
        String title = inputUser.readStringFromUser("Qual o titulo do livro: ");
        int id_autor = inputUser.readIntFromUser("Qual o id do autor do livro: ");
        livroModel.setIsbn(isbn);
        livroModel.setTitulo(title);
        livroModel.setId_autor(id_autor);

        livroService.adicionarLivro(livroModel.getIsbn(), livroModel.getTitulo(), livroModel.getId_autor());

    }

    private void deleteBook() {
        livroService.listarLivros();
        int id = inputUser.readIntFromUser("Qual o ID do Livro que deseja deletar: ");
        livroService.deletarLivro(id);
    }

    private void addAutor() {
        String nome = inputUser.readStringFromUser("Qual o nome do autor: ");
        autorModel.setName(nome);
        autorService.addAutor(autorModel.getName());
    }

    private void deleteAutor() {
        autorService.listarAutores();
        int id = inputUser.readIntFromUser("Qual o ID do autor que deseja deletar: ");
        autorService.deleteAutor(id);
    }

    private void deleteUsuario() {
        usuarioService.listarUsuarios();
        int id = inputUser.readIntFromUser("Qual o ID do usuario que deseja deletar: ");
        usuarioService.deletarUsuario(id);
    }
    private void update() {
        int cpf = inputUser.readIntFromUser("Qual seu cpf: ");
        String email = inputUser.readStringFromUser("Qual o novo email: ");
        usuarioModel.setEmail(email);
        usuarioModel.setCpf(cpf);
        usuarioService.atualizarUsuario(usuarioModel.getCpf(), usuarioModel.getEmail());
    }

}
