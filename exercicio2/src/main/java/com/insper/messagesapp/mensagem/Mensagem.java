package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.messagesapp.user.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Mensagem {

    private String id;

    @NonNull
    private String userId;

    @JsonIgnore
    private User user;

    private LocalDateTime time;
    
    @NonNull
    private String title;
}
