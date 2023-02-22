package entities.mensagem;

import entities.user.User;

import java.time.LocalDateTime;

public class MensagemArquivo extends Mensagem{

    private String filePath;
    private String caption;

    public MensagemArquivo(){}

    public MensagemArquivo(User user, LocalDateTime time, String title, String filePath, String caption) {
        super(user, time, title);
        this.filePath = filePath;
        this.caption = caption;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "User: " + user.getNome() + "\n"
                + "Data: " + time + "\n"
                + "Arquivo: " + filePath + "\n"
                + "Legenda: " + "\n" + caption;
    }
}
