package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.insper.messagesapp.user.User;

import lombok.*;

@Getter
@Setter
public class MensagemTexto extends Mensagem {

    @NonNull
    private String texto;

    public MensagemTexto(User user, LocalDateTime time, String title, String texto) {
        super(user, time, title);
        this.texto = texto;
    }
}
