package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.insper.messagesapp.user.User;

import lombok.*;

@Getter
@Setter
public class MensagemArquivo extends Mensagem{

    @NonNull
    private String filePath;

    @NonNull
    private String caption;

    public MensagemArquivo(User user, LocalDateTime time, String title, String filePath, String caption) {
        super(user, time, title);
        this.filePath = filePath;
        this.caption = caption;
    }
}
