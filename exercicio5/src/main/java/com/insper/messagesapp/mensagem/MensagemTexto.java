package com.insper.messagesapp.mensagem;

import jakarta.persistence.Entity;
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
