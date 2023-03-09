package com.insper.messagesapp.mensagem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.messagesapp.user.User;
import com.insper.messagesapp.user.UserService;

@Service
public class MensagemService {

    private List<Mensagem> mensagens = new ArrayList<>();
    
    @Autowired
    private UserService userService;

    public List<Mensagem> listarMensagens() {
        return mensagens;
    }

    public Mensagem buscaMensagem(String id) {
        for (Mensagem m : mensagens) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public List<Mensagem> listarMensagensByUser(String id) {
        User user = userService.buscarUser(id);

        if (user != null) {
            return user.getMensagemList();
        }
        return null;
    }

    public Mensagem postMensagem(Mensagem mensagem) {
        String id = mensagem.getUser().getId();
        User user = userService.buscarUser(id);

        if (user != null) {
            user.postMensagem(mensagem);
            mensagens.add(mensagem);
            return mensagem;
        }

        return null;
    }

}
