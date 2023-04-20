package com.insper.messagesapp.mensagem;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.messagesapp.user.User;
import com.insper.messagesapp.user.UserService;


@ExtendWith(MockitoExtension.class)
public class MensagemControllerTests {
    
    MockMvc mockMvc;

    @InjectMocks
    MensagemController mensagemController;

    @Mock
    MensagemService mensagemService;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mensagemController).build();
    }

    @Test
    void testListarMensagens() throws Exception {

        List<Mensagem> mensagens = generateMensagens();

        Mockito.when(mensagemService.listarMensagens()).thenReturn(mensagens);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagens), resp);

    }

    @Test
    void testSalvarMensagemTexto() throws Exception {

        List<Mensagem> mensagens = generateMensagens();
        MensagemTexto mensagem = (MensagemTexto) mensagens.get(0);

        Mockito.when(mensagemService.postMensagemTexto(ArgumentMatchers.any(MensagemTexto.class), ArgumentMatchers.eq(mensagem.getUser().getIdentifier()))).thenReturn(mensagem);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mensagemTexto/user-1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(mensagem)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagem), resp);

    }

    @Test
    void testSalvarMensagemArquivo() throws Exception {

        List<Mensagem> mensagens = generateMensagens();
        MensagemArquivo mensagem = (MensagemArquivo) mensagens.get(1);

        Mockito.when(mensagemService.postMensagemArquivo(ArgumentMatchers.any(MensagemArquivo.class), ArgumentMatchers.eq(mensagem.getUser().getIdentifier()))).thenReturn(mensagem);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mensagemArquivo/user-1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(mensagem)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagem), resp);

    }

    @Test
    void testBuscarMensagem() throws Exception {
        
        Mensagem mensagem = generateMensagens().get(0);

        Mockito.when(mensagemService.buscaMensagem(mensagem.getIdentifier())).thenReturn(mensagem);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem/mensagem-1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagem), resp);


        mensagem = generateMensagens().get(1);

        Mockito.when(mensagemService.buscaMensagem(mensagem.getIdentifier())).thenReturn(mensagem);

        result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem/mensagem-2"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        om = new ObjectMapper();

        resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagem), resp);
    
    }

    @Test
    void testListarMensagensByUser() throws Exception {

        List<Mensagem> mensagens = generateMensagens();
        mensagens.get(0)
            .getUser()
            .setMensagens(mensagens);

        Mockito.when(mensagemService.listarMensagensByUser("user-1")).thenReturn(mensagens.get(0).getUser().getMensagens());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem/user-1/user"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagens), resp);

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
