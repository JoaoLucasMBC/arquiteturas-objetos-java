package com.insper.messagesapp.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public User cadastrar(User user) {
        user.setId(UUID.randomUUID().toString());
        users.add(user);

        return user;
    }

    public List<User> listarUsers() {
        return users;
    }

    public User buscarUser(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
