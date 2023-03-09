package com.insper.messagesapp.mensagem;

import java.time.LocalDateTime;

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
    @NotNull
    @NotEmpty
    private User user;
    
    @NonNull
    private LocalDateTime time;
    
    @NonNull
    private String title;
}
