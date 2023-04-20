package com.insper.messagesapp.mensagem;


import jakarta.persistence.Entity;
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
