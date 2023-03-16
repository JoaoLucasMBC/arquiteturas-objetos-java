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
public class MensagemArquivo extends Mensagem{

    @NonNull
    private String filePath;

    @NonNull
    private String caption;

    public MensagemArquivo(String title, String filePath, String caption) {
        super(title);
        this.filePath = filePath;
        this.caption = caption;
    }
}
