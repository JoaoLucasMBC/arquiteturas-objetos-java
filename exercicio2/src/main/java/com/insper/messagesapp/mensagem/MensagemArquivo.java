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

    public MensagemArquivo(String userId, String title, String filePath, String caption) {
        super(userId, title);
        this.setTime(LocalDateTime.now());
        this.filePath = filePath;
        this.caption = caption;
    }
}
