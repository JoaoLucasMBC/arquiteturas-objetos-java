package com.insper.messagesapp.mensagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemTextoRepository extends JpaRepository<MensagemTexto, Integer> {

    public MensagemTexto findByIdentifier(String identifier);
}
