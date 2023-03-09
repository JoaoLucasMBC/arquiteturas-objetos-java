package entities.user;

import entities.mensagem.Mensagem;
import entities.mensagem.MensagemArquivo;
import entities.mensagem.MensagemTexto;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    public void cadastrar(String nome, Integer idade) {
        User user = new User(nome, idade);

        users.add(user);
    }

    public List<User> listarUsers() {
        return users;
    }

    public User buscarUser(String nome) {
        User user = null;
        for (User u : users) {
            if (u.getNome().equals(nome)) {
                user = u;
                break;
            }
        }

        return user;
    }

    public List<Mensagem> listarMensagens(String nome) {
        User user = this.buscarUser(nome);

        if (user != null) {
            return user.getMensagemList();
        }
        return null;
    }

    public void postMensagem(String userName, LocalDateTime time, String title, String texto) {
        User user = this.buscarUser(userName);

        if (user != null) {
            Mensagem msg = new MensagemTexto(user, time, title, texto);

            user.postMensagem(msg);
        }
    }

    public void postMensagem(String userName, LocalDateTime time, String title, String filePath, String caption) {
        User user = this.buscarUser(userName);

        if (user != null) {
            Mensagem msg = new MensagemArquivo(user, time, title, filePath, caption);

            user.postMensagem(msg);
        }
    }
}
