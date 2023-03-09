package com.insper.messagesapp.mensagem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/mensagem")
    public List<Mensagem> listaMensagens() {
        return mensagemService.listarMensagens();
    }

    @PostMapping("/mensagemText")
    public Mensagem salvarMensagemTexto(@RequestBody @Valid MensagemTexto mensagem) {
        return mensagemService.postMensagem(mensagem);
    }

    @PostMapping("/mensagemArquivo")
    public Mensagem salvarMensagemArquivo(@RequestBody @Valid MensagemArquivo mensagem) {
        return mensagemService.postMensagem(mensagem);
    }

    @GetMapping("/mensagem/{id}")
    public Mensagem buscaMensagem(@PathVariable("id") String id) {
        return mensagemService.buscaMensagem(id);
    }

    @GetMapping("/mensagem/{userId}/user")
    public List<Mensagem> listaMensagensByUser(@PathVariable("userId") String userId) {
        return mensagemService.listarMensagensByUser(userId);
    }
}
