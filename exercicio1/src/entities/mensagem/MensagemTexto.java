package entities.mensagem;

import entities.user.User;

import java.time.LocalDateTime;

public class MensagemTexto extends Mensagem {

    private String texto;

    public MensagemTexto(){}

    public MensagemTexto(User user, LocalDateTime time, String title, String texto) {
        super(user, time, title);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "User: " + user.getNome() + "\n"
                + "Data: " + time + "\n"
                + "Conteúdo: " + "\n" + texto;
    }
}
