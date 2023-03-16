package com.insper.messagesapp.mensagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemArquivoRepository extends JpaRepository<MensagemArquivo, Integer> {

    public MensagemArquivo findByIdentifier(String identifier);
}
