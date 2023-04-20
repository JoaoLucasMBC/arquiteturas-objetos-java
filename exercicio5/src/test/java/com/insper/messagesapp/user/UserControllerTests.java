package com.insper.messagesapp.user;

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


@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    void testListarUsers() throws Exception {
        
        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userService.listarUsers()).thenReturn(users);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(users), resp);
    }

    @Test
    void testBuscarUser() throws Exception {

        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        Mockito.when(userService.buscarUser("user-1")).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/user-1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(user), resp);

    }

    @Test
    void testSalvarUser() throws Exception {

        User user = new User();
        user.setIdentifier("user-1");
        user.setNome("User 1");
        user.setIdade(19);
        user.setId(1);

        Mockito.when(userService.cadastrar(ArgumentMatchers.any(User.class))).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(user)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(user), resp);

    }
    
}
