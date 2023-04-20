package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.messagesapp.user.User;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private String identifier;

    private LocalDateTime time;
    
    @NonNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
