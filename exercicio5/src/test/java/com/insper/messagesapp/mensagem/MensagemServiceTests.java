package com.insper.messagesapp.mensagem;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insper.messagesapp.user.User;
import com.insper.messagesapp.user.UserService;


@ExtendWith(MockitoExtension.class)
public class MensagemServiceTests {
    
    @InjectMocks
    MensagemService mensagemService;

    @Mock
    MensagemTextoRepository mensagemTextoRepository;

    @Mock
    MensagemArquivoRepository mensagemArquivoRepository;

    @Mock
    UserService userService;


    @Test
    void testListarMensagens() {

        List<Mensagem> mensagens = generateMensagens();
        List<MensagemTexto> m1 = new ArrayList<>();
        m1.add((MensagemTexto) mensagens.get(0));
        List<MensagemArquivo> m2 = new ArrayList<>();
        m2.add((MensagemArquivo) mensagens.get(1));

        Mockito.when(mensagemTextoRepository.findAll()).thenReturn(m1);
        Mockito.when(mensagemArquivoRepository.findAll()).thenReturn(m2);

        List<Mensagem> resp = mensagemService.listarMensagens();

        Assertions.assertEquals(mensagens.size(), resp.size());
        Assertions.assertEquals(mensagens.get(0).getIdentifier(), resp.get(0).getIdentifier());
        Assertions.assertEquals(mensagens.get(1).getIdentifier(), resp.get(1).getIdentifier());

    }

    @Test
    void testListarMensagensTexto() {

        List<Mensagem> mensagens = generateMensagens();
        List<MensagemTexto> m1 = new ArrayList<>();
        m1.add((MensagemTexto) mensagens.get(0));

        Mockito.when(mensagemTextoRepository.findAll()).thenReturn(m1);

        List<MensagemTexto> resp = mensagemService.listarMensagensTexto();

        Assertions.assertEquals(m1.size(), resp.size());
        Assertions.assertEquals(m1.get(0).getIdentifier(), resp.get(0).getIdentifier());

    }

    @Test
    void testListarMensagensArquivo() {

        List<Mensagem> mensagens = generateMensagens();
        List<MensagemArquivo> m2 = new ArrayList<>();
        m2.add((MensagemArquivo) mensagens.get(1));

        Mockito.when(mensagemArquivoRepository.findAll()).thenReturn(m2);

        List<MensagemArquivo> resp = mensagemService.listarMensagensArquivo();

        Assertions.assertEquals(m2.size(), resp.size());
        Assertions.assertEquals(m2.get(0).getIdentifier(), resp.get(0).getIdentifier());

    }

    @Test
    void testListarMensagensByUser() {

        List<Mensagem> mensagens = generateMensagens();
        mensagens.get(0)
            .getUser()
            .setMensagens(mensagens);

        Mockito.when(userService.buscarUser("user-1")).thenReturn(mensagens.get(0).getUser());

        List<Mensagem> resp = mensagemService.listarMensagensByUser("user-1");

        Assertions.assertEquals(mensagens.size(), resp.size());
        Assertions.assertEquals(mensagens.get(0).getIdentifier(), resp.get(0).getIdentifier());
        Assertions.assertEquals(mensagens.get(1).getIdentifier(), resp.get(1).getIdentifier());

    }

    @Test
    void postMensagemTexto() {

        List<Mensagem> mensagens = generateMensagens();
        MensagemTexto mensagem = (MensagemTexto) mensagens.get(0);

        Mockito.when(mensagemTextoRepository.save(mensagem)).thenReturn(mensagem);
        Mockito.when(userService.buscarUser("user-1")).thenReturn(mensagem.getUser());

        Mensagem resp = mensagemService.postMensagemTexto(mensagem, "user-1");

        Assertions.assertEquals(mensagem.getIdentifier(), resp.getIdentifier());
        Assertions.assertEquals(mensagem.getTitle(), resp.getTitle());
        Assertions.assertEquals(mensagem.getTexto(), ((MensagemTexto) resp).getTexto());

    }

    @Test
    void postMensagemArquivo() {

        List<Mensagem> mensagens = generateMensagens();
        MensagemArquivo mensagem = (MensagemArquivo) mensagens.get(1);

        Mockito.when(mensagemArquivoRepository.save(mensagem)).thenReturn(mensagem);
        Mockito.when(userService.buscarUser("user-1")).thenReturn(mensagem.getUser());

        Mensagem resp = mensagemService.postMensagemArquivo(mensagem, "user-1");

        Assertions.assertEquals(mensagem.getIdentifier(), resp.getIdentifier());
        Assertions.assertEquals(mensagem.getTitle(), resp.getTitle());
        Assertions.assertEquals(mensagem.getFilePath(), ((MensagemArquivo) resp).getFilePath());
        Assertions.assertEquals(mensagem.getCaption(), ((MensagemArquivo) resp).getCaption());

    }


    public static List<Mensagem> generateMensagens() {
        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);
    
        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setIdentifier("mensagem-1");
        mensagem.setTitle("Mensagem 1");
        mensagem.setTexto("Texto 1");
        mensagem.setId(1);
        mensagem.setUser(user);

        MensagemArquivo mensagemArquivo = new MensagemArquivo();
        mensagemArquivo.setIdentifier("mensagem-2");
        mensagemArquivo.setTitle("Mensagem 2");
        mensagemArquivo.setFilePath("Arquivo 2");
        mensagemArquivo.setCaption("Caption 2");
        mensagemArquivo.setId(2);
        mensagemArquivo.setUser(user);

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(mensagem);
        mensagens.add(mensagemArquivo);

        return mensagens;
    }
    

}
