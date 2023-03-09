package com.insper.messagesapp.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listarUsers() {
        return userService.listarUsers();
    }

    @GetMapping("/{id}")
    public User buscarUser(@PathVariable("id") String id) {
        return userService.buscarUser(id);
    }

    @PostMapping
    public User salvarUser(@RequestBody @Valid User user) {
        return userService.cadastrar(user);
    }


}
