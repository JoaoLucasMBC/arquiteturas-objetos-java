package com.insper.messagesapp.user;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.messagesapp.mensagem.Mensagem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(name = "identifier")
    private String identifier;

    @NonNull
    @NotNull
    @NotEmpty
    private String nome;
   
    @NonNull
    private Integer idade;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Mensagem> mensagens;

}
