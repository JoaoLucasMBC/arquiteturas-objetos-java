package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.insper.messagesapp.user.User;

import lombok.*;

@Getter
@Setter
public class MensagemTexto extends Mensagem {

    @NonNull
    private String texto;

    public MensagemTexto(String userId, String title, String texto) {
        super(userId, title);
        this.setTime(LocalDateTime.now());
        this.texto = texto;
    }
}
