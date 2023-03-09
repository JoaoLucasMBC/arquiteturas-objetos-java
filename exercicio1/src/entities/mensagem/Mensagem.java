package entities.mensagem;

import entities.user.User;

import java.time.LocalDateTime;

public class Mensagem {

    protected User user;
    protected LocalDateTime time;
    protected String title;

    public Mensagem(){}

    public Mensagem(User user, LocalDateTime time, String title) {
        this.user = user;
        this.time = time;
        this.title = title;
    }

    public User getUser() {
        return user;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "User: " + user.getNome() + "\n"
                + "Data: " + time + "\n";
    }
}
