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

    @PostMapping("/mensagemTexto/{userIdentifier}")
    public Mensagem salvarMensagemTexto(@RequestBody @Valid MensagemTexto mensagem, @PathVariable("userIdentifier") String userIdentifier) {
        return mensagemService.postMensagemTexto(mensagem, userIdentifier);
    }

    @PostMapping("/mensagemArquivo/{userIdentifier}")
    public Mensagem salvarMensagemArquivo(@RequestBody @Valid MensagemArquivo mensagem, @PathVariable("userIdentifier") String userIdentifier) {
        return mensagemService.postMensagemArquivo(mensagem, userIdentifier);
    }

    @GetMapping("/mensagem/{identifier}")
    public Mensagem buscaMensagem(@PathVariable("identifier") String identifier) {
        return mensagemService.buscaMensagem(identifier);
    }

    @GetMapping("/mensagem/{userIdentifier}/user")
    public List<Mensagem> listaMensagensByUser(@PathVariable("userIdentifier") String userIdentifier) {
        return mensagemService.listarMensagensByUser(userIdentifier);
    }
}
