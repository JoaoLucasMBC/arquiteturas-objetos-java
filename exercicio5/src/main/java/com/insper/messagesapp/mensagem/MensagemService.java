package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.messagesapp.user.User;
import com.insper.messagesapp.user.UserService;

@Service
public class MensagemService {

    @Autowired
    private MensagemArquivoRepository mensagemArquivoRepository;
    @Autowired
    private MensagemTextoRepository mensagemTextoRepository;
    @Autowired
    private UserService userService;

    public List<Mensagem> listarMensagens() {
        List<Mensagem> mensagens= new ArrayList<>();

        mensagens.addAll(listarMensagensTexto());
        mensagens.addAll(listarMensagensArquivo());

        return mensagens;
    }

    public List<MensagemArquivo> listarMensagensArquivo() {
        return mensagemArquivoRepository.findAll();
    }

    public List<MensagemTexto> listarMensagensTexto() {
        return mensagemTextoRepository.findAll();
    }

    public Mensagem buscaMensagem(String identifier) {
        Mensagem mensagemTexto = mensagemTextoRepository.findByIdentifier(identifier);
        if (mensagemTexto != null) {
            return mensagemTexto;
        } else {
            return (Mensagem) mensagemArquivoRepository.findByIdentifier(identifier);
        }
    }


    public List<Mensagem> listarMensagensByUser(String identifier) {
        User user = userService.buscarUser(identifier);

        if (user != null) {
            return user.getMensagens();
        }
        return null;
    }

    public MensagemTexto postMensagemTexto(MensagemTexto mensagem, String userIdentifier) {
        User user = userService.buscarUser(userIdentifier);

        if (user != null) {
            mensagem.setIdentifier(UUID.randomUUID().toString());
            mensagem.setUser(user);
            mensagem.setTime(LocalDateTime.now());

            return mensagemTextoRepository.save(mensagem);
        }

        return null;
    }

    public MensagemArquivo postMensagemArquivo(MensagemArquivo mensagem, String userIdentifier) {
        User user = userService.buscarUser(userIdentifier);

        if (user != null) {
            mensagem.setIdentifier(UUID.randomUUID().toString());
            mensagem.setUser(user);
            mensagem.setTime(LocalDateTime.now());

            return mensagemArquivoRepository.save(mensagem);
        }

        return null;
    }

}
