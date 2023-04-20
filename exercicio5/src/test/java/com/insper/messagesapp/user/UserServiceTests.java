package com.insper.messagesapp.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;


    @Test
    void testCadastrar() {
        
        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User resp = userService.cadastrar(user);

        Assertions.assertEquals(user.getId(), resp.getId());
        Assertions.assertEquals(user.getIdentifier(), resp.getIdentifier());
        Assertions.assertEquals(user.getNome(), resp.getNome());
        Assertions.assertEquals(user.getIdade(), resp.getIdade());

    }

    @Test
    void testListarUsers() {

        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        
        List<User> resp = userService.listarUsers();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals(user.getId(), resp.get(0).getId());

    }

    @Test
    void testBuscarUser() {

        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        Mockito.when(userRepository.findByIdentifierIgnoreCase("user-1")).thenReturn(user);
        
        User resp = userService.buscarUser("user-1");

        Assertions.assertEquals(user.getId(), resp.getId());
        Assertions.assertEquals(user.getIdentifier(), resp.getIdentifier());
        Assertions.assertEquals(user.getNome(), resp.getNome());
        Assertions.assertEquals(user.getIdade(), resp.getIdade());

    }

}
