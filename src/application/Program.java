package application;

import entities.mensagem.Mensagem;
import entities.mensagem.MensagemTexto;
import entities.user.User;
import entities.user.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();

        String operacao = "";

        while (!operacao.equals("6")) {
            System.out.println("1 - Cadastrar User \n2 - Listar Users \n3- Encontrar User \n4 - Listar Mensagens \n5 - Postar Mensagem \n6 - Sair");
            System.out.println();
            System.out.print("Operação: ");
            operacao = sc.nextLine();

            System.out.println();

            if (operacao.equals("1")) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Idade: ");
                int idade = sc.nextInt();
                sc.nextLine();

                userService.cadastrar(nome, idade);

            }

            if (operacao.equals("2")) {
                for (User u : userService.listarUsers()) {
                    System.out.println(u);
                }
            }

            if (operacao.equals("3")) {
                System.out.print("Insira o nome do User: ");
                String nome = sc.nextLine();
                User user = userService.buscarUser(nome);

                if (user != null) {
                    System.out.println(user);
                } else {
                    System.out.println("User não encontrado");
                }
            }

            if (operacao.equals("4")) {
                System.out.print("Insira o nome do User: ");
                String nome = sc.nextLine();
                List<Mensagem> mensagens = userService.listarMensagens(nome);
                System.out.println();

                if (mensagens == null) {
                    System.out.println("User não encontrado");
                } else if (mensagens.size() == 0) {
                    System.out.println("User não possui mensagens");
                } else {
                    for (Mensagem msg : mensagens) {
                        System.out.println(msg);
                        System.out.println();
                    }
                }
            }

            if (operacao.equals("5")) {
                System.out.print("Insira o nome do User: ");
                String nome = sc.nextLine();

                System.out.print("Qual o título da mensagem? ");
                String title = sc.nextLine();

                System.out.print("Qual o tipo da mensagem (texto/arquivo)? ");
                String tipo = sc.nextLine();

                if (tipo.equals("texto")) {
                    System.out.println("Digite o seu texto: ");
                    String texto = sc.nextLine();

                    userService.postMensagem(nome, LocalDateTime.now(), title, texto);
                } else if (tipo.equals("arquivo")) {
                    System.out.print("Insira o arquivo: ");
                    String filePath = sc.nextLine();

                    System.out.println("Digite a legenda da imagem: ");
                    String caption = sc.nextLine();

                    userService.postMensagem(nome, LocalDateTime.now(), title, filePath, caption);
                }
            }

            if (!operacao.equals("6")) {
                System.out.println();
                System.out.print("Pressione ENTER para continuar");
                sc.nextLine();
                System.out.println();
            }
        }

    }
}