package com.insper.messagesapp.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User cadastrar(User user) {
        user.setIdentifier(UUID.randomUUID().toString());
        userRepository.save(user);

        return user;
    }

    public List<User> listarUsers() {
        return userRepository.findAll();
    }

    public User buscarUser(String identifier) {
        return userRepository.findByIdentifierIgnoreCase(identifier);
    }
}
