package com.insper.messagesapp.user;


import java.util.ArrayList;
import java.util.List;

import com.insper.messagesapp.mensagem.Mensagem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    @NonNull
    @NotNull
    @NotEmpty
    private String nome;
   
    @NonNull
    private Integer idade;

    private List<Mensagem> mensagemList = new ArrayList<>();

    public void postMensagem(Mensagem msg) {
        mensagemList.add(msg);
    }

    public void eraseMensagem(Mensagem msg) {
        mensagemList.remove(msg);
    }
}
