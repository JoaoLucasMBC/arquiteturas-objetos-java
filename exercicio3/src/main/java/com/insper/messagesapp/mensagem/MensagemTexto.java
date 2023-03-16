package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.insper.messagesapp.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MensagemTexto extends Mensagem {

    @NonNull
    private String texto;

    public MensagemTexto(String title, String texto) {
        super(title);
        this.texto = texto;
    }
}
